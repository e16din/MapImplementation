package me.map

import org.junit.Test
import kotlin.collections.set


class HashMapTest {

    @Test
    fun addStringKey() {
        val map = HashMap<String>()
        map.put("Key", "Value1")
        assert(map["Key"] == "Value1")
    }

    @Test
    fun setStringKey() {
        val map = HashMap<String>()
        map.put("Key", "Value1")
        map["Key"] = "Value2"

        assert(map["Key"] == "Value2")
    }

    @Test
    fun removeStringKey() {
        val map = HashMap<String>()
        map.put("Key", "Value1")
        map.remove("Key")

        assert(map["Key"] == null)
    }

    @Test
    fun addAnyKey() {
        val map = HashMap<String>()
        map.put(Unit, "Value1")
        assert(map[Unit] == "Value1")
    }

    @Test
    fun setAnyKey() {
        val map = HashMap<String>()
        map.put(Unit, "Value1")
        map[Unit] = "Value2"
        assert(map[Unit] == "Value2")
    }

    @Test
    fun removeAnyKey() {
        val map = HashMap<String>()
        map.put(Unit, "Value1")
        map.remove(Unit)

        assert(map[Unit] == null)
    }

    @Test
    fun productivityHashMap() { // 1 401 ms, 2 954 ms
        val start = System.currentTimeMillis()
        val map = kotlin.collections.HashMap<String, String>()
        repeat(1024*1024) {
            map.put("Key$it", "Value$it")
        }
        map["Key5"] = "Value5Edited"
        map["Key6"] = "Value6Edited"
        map["Key9"] = "Value9Edited"
        map["Key7"] = "Value7Edited"
        map.remove("Key100")
        map.remove("Key1005")
        map.remove("Key100500")
        map["Key3"] = "Value3Edited"
        println(map["Key5"])
        val finish = System.currentTimeMillis()
        println("time: ${finish-start}Ms")
    }

    @Test
    fun productivityNewMap() { // 1 771 ms, 2 762 ms
        val start = System.currentTimeMillis()
        val map = HashMap<String>(bucketsCount = 1024*1024)
        repeat(1024*1024) {
            map.put("Key$it", "Value$it")
           // println("i: $it")
        }
        map["Key5"] = "Value5Edited"
        map["Key6"] = "Value6Edited"
        map["Key9"] = "Value9Edited"
        map["Key7"] = "Value7Edited"
        map.remove("Key100")
        map.remove("Key1005")
        map.remove("Key100500")
        map["Key3"] = "Value3Edited"
        println(map["Key5"])
        val finish = System.currentTimeMillis()
        println("time: ${finish-start}Ms")
    }

    // NOTE: То как принято говорить о реализации HashMap на собеседованиях,
    // без дополнительных оптимизаций работает ОЧЕНЬ медленно на больших объемах данных,
    // судя по всему тут поможет добавление новых баккетов
    // при возрастании кол-ва входящих данных

    // да, проблема решается если установить кол-во баккетов соответствующее кол-ву данных
}