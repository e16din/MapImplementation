package me.map

import org.junit.Test
import kotlin.collections.set


class MapTest {

    @Test
    fun addStringKey() {
        val map = Map<String>()
        map.add("Key", "Value1")
        assert(map["Key"] == "Value1")
    }

    @Test
    fun setStringKey() {
        val map = Map<String>()
        map.add("Key", "Value1")
        map["Key"] = "Value2"
        assert(map["Key"] == "Value2")
    }

    @Test
    fun removeStringKey() {
        val map = Map<String>()
        map.add("Key", "Value1")
        map.remove("Key")

        assert(map["Key"] == null)
    }

    @Test
    fun addAnyKey() {
        val map = Map<String>()
        map.add(Unit, "Value1")
        assert(map[Unit] == "Value1")
    }

    @Test
    fun setAnyKey() {
        val map = Map<String>()
        map.add(Unit, "Value1")
        map[Unit] = "Value2"
        assert(map[Unit] == "Value2")
    }

    @Test
    fun removeAnyKey() {
        val map = Map<String>()
        map.add(Unit, "Value1")
        map.remove(Unit)

        assert(map[Unit] == null)
    }

    @Test
    fun productivityHashMap() {
        val start = System.currentTimeMillis()
        val map = HashMap<String, String>()
        repeat(1005009) {
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
    fun productivityNewMap() {
        val start = System.currentTimeMillis()
        val map = Map<String>()
        repeat(1005009) {
            map.add("Key$it", "Value$it")
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


    // NOTE: скорость и той и другой реализации примерно на одном уровне с ключами Int,
    // с ключами String на моей машине HashMap проигрывает (604ms у Map против 760ms у HashMap)

    // по памяти отличаются примерно так как отличается ArrayList от LinkedList за счет внутренней реализации

    // (отмечу что в моей реализации Map не используются hashCode() и разбиение на баккеты,
    // вместо этого используется хранение в обычном динамическом массиве)

    // такая произовдительность стала возможной за счет разделения реализации put() на add() и set()
}