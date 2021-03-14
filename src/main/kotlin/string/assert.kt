package string


infix fun String.shouldBe(other: String) {
    this.apply {
        assertEquals(this, other)
    }
}

infix fun String.shouldBe2(other: String) = assertEquals(this, other)

fun assertEquals(a: String, b: String) {
    assert(a == b) {
        "应该相等好不"
    }

    val block: () -> String = { "应该相等好不" }
    assert(a == b, block)
    assert(a == b) { "应该相等好不" }
}


fun main() {
    "abc" shouldBe "abc"
    "efg" shouldBe "def"
    "a" shouldBe "2"

    "abcdefg" shouldBe2 "fuck"
}