package me.map

import java.util.LinkedList

class KeyValue<T>(val key:Any, var value:T)
class HashMap<T>(val bucketsCount: Int = 16) {
    private val buckets = mutableListOf<LinkedList<KeyValue<T>>>().apply {
        repeat(bucketsCount) {
            add(LinkedList<KeyValue<T>>())
        }
    }

    fun put(key: Any, value: T) {
        val hashCode = hashOf(key)
        val bucketIndex = hashCode.mod(buckets.size)

        val bucket = buckets[bucketIndex]
        for(i in 0 until bucket.size) {
            if(bucket[i].key == key) {
                bucket[i].value = value
                return
            }
        }

        // else
        bucket.add(KeyValue(key, value))
    }

    fun remove(key: Any) {
        val hashCode = hashOf(key)
        val bucketIndex = hashCode.mod(buckets.size)
        buckets[bucketIndex].removeIf { it.key == key }
    }

    operator fun set(key: Any, value: T) {
        put(key, value)
    }

    operator fun get(key: Any): T? {
        val hashCode = hashOf(key)
        val bucketIndex = hashCode.mod(buckets.size)

        return buckets[bucketIndex].firstOrNull{ it.key == key }?.value
    }

    private fun hashOf(data:Any):Int {
        return data.hashCode() // todo: add my own implementation
    }
}