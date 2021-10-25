// Original bug: KT-14000

package com.test

interface BaseEntity

interface Repository<T : BaseEntity>

interface Person : BaseEntity

class BaseRepository<T : BaseEntity> : Repository<T>
