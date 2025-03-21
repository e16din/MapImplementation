### Моя реализация Map

Задумал я реализовать свой HashMap для показательного примера, в два этапа: 
1. реализовать словарь максимально просто 
2. затем улучшить производительность до уровня HashMap, добавить разбиение на баккеты и использовать хешкоды 

Но вышло так что моя простая реализация Map оказалась такой же шустрой как и HashMap, даже без разбиения на баккеты, в некоторых кейсах даже шустрее :)

Вот и вся реализация:

```kotlin
class Map<T> {
    val values = mutableListOf<T>()
    val keys = mutableListOf<Any>()

    fun add(key: Any, value: T) {
        keys.add(key)
        values.add(keys.size - 1, value)
    }

    fun remove(key: Any) {
        val i = keys.indexOf(key)
        values.removeAt(i)
        keys.remove(key)
    }

    operator fun set(key: Any, value: T) {
        val i = keys.indexOf(key)
        values[i] = value
    }


    operator fun get(key: Any): T? {
        val i = keys.indexOf(key)
        return (if (i < 0) null else values[i])
    }
}
```

Там еще тесты есть.
