package string

import java.io.File
import java.net.URL


inline fun String.contentFromUrl(block: (URL) -> String) {
    println(block(URL(this)))
}


fun main() {
    "http://www.baidu.com".contentFromUrl { url ->
        val readText = url.readText()
        File("temp.html").run {
            this.delete()
            this.createNewFile()
            this.writeText(readText)
        }
        readText
    }
}