// Original bug: KT-19084

package round1A

import java.util.*

/**
 * Created by gabryel on 14/04/17.
 */
fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    for (case in 1..sc.nextInt()) {
        val facts = BattleFacts(RPGChar(sc.nextInt(), sc.nextInt()), RPGChar(sc.nextInt(), sc.nextInt()),
                sc.nextInt(), sc.nextInt())

        println("Case #$case: ${SolverC().solve(facts)}")
    }
}

class SolverC {
    fun solve(facts: BattleFacts): String {
        return try {
            debuffPhase(Turn(facts)).mapNotNull {
                try {
                    buffAndAttack(it)
                } catch (e: Exception) {
                    null
                }
            }.minBy(Turn::turns)?.turns?.toString() ?: "IMPOSSIBLE"
        } catch (e: Exception) {
            "IMPOSSIBLE"
        }
    }

    /**
     * Returns all possible debuffed States
     */
    private fun debuffPhase(current: Turn): List<Turn> {
        var turns = listOf(current)
        if (current.facts.debuff == 0) {
            return turns
        }

        var turn = current
        while (turn.knight.damage != 0) {
            turn = turn.debuff(debuffsForNextThreshold(turn))
            turns = turns.plus(turn)
        }

        return turns
    }

    /**
     * Discovers minimum number of debuffs needed to transport to next threshold of damage.
     * A threshold of damage is a moment that the enemy damage changes the number of hits needed
     * to kill the dragon.
     */
    private fun debuffsForNextThreshold(turn: Turn): Int {
        val turnsToDie = turnsToKill(turn.knight, turn.facts.initDragon)
        val nextThreshold = Math.ceil(turn.facts.initDragon.health.toDouble() / turnsToDie) - 1
        return Math.ceil((turn.knight.damage - nextThreshold) / turn.facts.debuff).toInt()
    }

    /**
     * Returns the turn that the knight dies. Doesn't work for IMPOSSIBLE cases.
     * Consider the number calculated on BattleFacts to know the number of buffs
     */
    private fun buffAndAttack(current: Turn): Turn {
        return current.buff(current.facts.buffsNeeded).attack(current.facts.attacksNeeded)
    }
}

data class RPGChar(val health: Int, val damage: Int) {
    fun recoverTo(health: Int) = RPGChar(health, this.damage)

    fun buff(buff: Int) = RPGChar(health, damage + buff)

    fun debuff(debuff: Int) = RPGChar(health, maxOf(0, damage - debuff))
}

open class Turn(val facts: BattleFacts) {
    open val cures: Int = 0
    open val buffs: Int = 0
    open val attacks: Int = 0
    open val debuffs: Int = 0

    val turns: Int by lazy { cures + attacks + buffs + debuffs }

    open val dragon: RPGChar = facts.initDragon
    open val knight: RPGChar = facts.initKnight

    fun debuff(times: Int = 1): Turn {
        return turn(times, Action.DEBUFF)
    }

    fun attack(times: Int = 1): Turn {
        return turn(times, Action.ATTACK)
    }

    fun buff(times: Int = 1): Turn {
        return turn(times, Action.BUFF)
    }

    private fun turn(times: Int, action: Action): Turn {
        return if (times == 0) this
        else NextTurn(this, action, times)
    }
}

class NextTurn(turn: Turn, action: Action, times: Int) : Turn(turn.facts) {
    override val cures: Int
    override val buffs: Int = turn.buffs + if (action == Action.BUFF) times else 0
    override val debuffs: Int = turn.debuffs + if (action == Action.DEBUFF) times else 0
    override val attacks: Int = turn.attacks + if (action == Action.ATTACK) times else 0

    override val dragon: RPGChar
    override val knight: RPGChar

    init {
        val totalHealth = facts.initDragon.health
        var lastDragon = turn.dragon
        var lastKnight = turn.knight
        var curedInLastTurn = false

        var curesNeeded = 0
        var timesRemaining = times
        var dragonHP = lastDragon.health
        var knightDamage = lastKnight.damage
        when (action) {
            Action.DEBUFF -> {
                while (timesRemaining > 0) {
                    var simulatedKnightDamage = Math.max(0, knightDamage - facts.debuff)
                    var turnsBeforeNextCure = turnsToKill(simulatedKnightDamage, dragonHP) - 1
                    var commonTurnsBeforeNextCure = turnsToKill(simulatedKnightDamage, totalHealth) - 2

                    if (turnsBeforeNextCure < 1 && commonTurnsBeforeNextCure < 1) {
                        throw ImpossibleException()
                    }

                    if (turnsBeforeNextCure == 0) {
                        dragonHP = totalHealth - knightDamage
                        curesNeeded++
                        continue
                    }

                    val cycles = (timesRemaining / turnsBeforeNextCure) - 1
                    if (cycles > 1 && turnsBeforeNextCure == commonTurnsBeforeNextCure) {
                        knightDamage = Math.max(0, knightDamage - (cycles * turnsBeforeNextCure * facts.debuff))
                        timesRemaining -= cycles * turnsBeforeNextCure
                        dragonHP = totalHealth - knightDamage
                        curesNeeded += cycles
                    } else {
                        knightDamage = simulatedKnightDamage
                        dragonHP -= knightDamage
                        timesRemaining--
                    }
                }

                lastKnight = lastKnight.debuff(times * facts.debuff)
            }
            Action.BUFF -> {
                while (timesRemaining > 0) {
                    curedInLastTurn = if (dragonHP <= knightDamage) {
                        if (curedInLastTurn) throw ImpossibleException()

                        dragonHP = totalHealth
                        curesNeeded++
                        true
                    } else {
                        timesRemaining--
                        false
                    }

                    dragonHP -= knightDamage
                }

                lastDragon = lastDragon.buff(times * facts.buff)
            }
            Action.ATTACK -> {
                val knightHP = lastKnight.health - timesRemaining * lastDragon.damage
                val turnsBeforeNextCure = turnsToKill(knightDamage, dragonHP) - 1
                val commonTurnsBeforeNextCure = turnsToKill(knightDamage, totalHealth) - 2

                timesRemaining -= if (knightHP <= 0) 1 else 0
                if (turnsBeforeNextCure < 1 && commonTurnsBeforeNextCure < 1 && timesRemaining != 0) {
                    throw ImpossibleException()
                }

                val differenceFromLoop = turnsBeforeNextCure - commonTurnsBeforeNextCure
                if (differenceFromLoop > 0) {
                    val turns = Math.min(differenceFromLoop, timesRemaining)
                    dragonHP -= turns * knightDamage
                    timesRemaining -= turns
                } else if (differenceFromLoop < 0) {
                    val turns = Math.min(turnsBeforeNextCure, timesRemaining)
                    dragonHP = totalHealth - knightDamage
                    timesRemaining -= turns
                    if (timesRemaining != 0) curesNeeded++
                }

                if (timesRemaining > 0) {
                    val cycles = (timesRemaining / commonTurnsBeforeNextCure)
                    if (cycles > 0) {
                        timesRemaining -= cycles * commonTurnsBeforeNextCure
                        curesNeeded += cycles
                        dragonHP = totalHealth - knightDamage
                    }

                    if (timesRemaining == 0){
                        curesNeeded--
                    }

                    dragonHP -= timesRemaining * knightDamage
                }

                lastKnight = lastKnight.recoverTo(knightHP)
            }
        }

        cures = turn.cures + curesNeeded
        dragon = lastDragon.recoverTo(dragonHP)
        knight = lastKnight
    }
}

enum class Action { ATTACK, BUFF, DEBUFF }

class BattleFacts(val initDragon: RPGChar, val initKnight: RPGChar, val buff: Int, val debuff: Int) {

    val buffsNeeded = getNeededBuffs()

    val attacksNeeded = turnsToKill(initDragon.damage + (buffsNeeded * buff), initKnight.health)

    private inline fun getNeededBuffs(): Int {
        var buffsNeeded = 0
        var currentDragon = initDragon
        var buffedDragon = currentDragon.buff(buff)
        while (turnsToKill(currentDragon, initKnight) > 1 + turnsToKill(buffedDragon, initKnight)) {
            buffsNeeded++

            currentDragon = buffedDragon
            buffedDragon = buffedDragon.buff(buff)
        }

        return buffsNeeded
    }
}

private inline fun turnsToKill(attacker: RPGChar, defender: RPGChar) =
        turnsToKill(attacker.damage, defender.health)

private inline fun turnsToKill(attack: Int, health: Int) =
        Math.ceil(health.toDouble() / attack).toInt()

private class ImpossibleException : Exception()