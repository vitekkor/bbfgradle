// Original bug: KT-31225

import org.intellij.lang.annotations.Language

@Language("ruby")
fun `test foo`() = doTest("""
  Song = Struct.new(:title, :name, :length)
  song_file = File.new("songdata.txt")
  songs = []

  song_file.each_line do |line|
    file, length, name, title = line.chomp.split(/\s*\|\s*/)

    songs << Song.new(title, name, length)
  end


  p songs

  def foo(a)
    p a
  end
""".trimIndent())

fun `test without language injection`() = doTest("""
  Song = Struct.new(:title, :name, :length)
  song_file = File.new("songdata.txt")
  songs = []

  song_file.each_line do |line|
  file, length, name, title  = line.chomp.split(/\s*\|\s*/)

  songs << Song.new(title, name, length)
  end


  p songs
""".trimIndent())

private fun doTest(@Language("ruby") rubyCode: String) {
  // bla bla bla
}

