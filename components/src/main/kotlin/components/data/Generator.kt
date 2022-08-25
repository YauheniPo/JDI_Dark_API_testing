package me.tango.components.data

import org.slf4j.LoggerFactory
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import javax.imageio.ImageIO
import kotlin.random.Random


object Generator {
    private const val DIGIT_COUNT = 10
    private const val ALPHANUMERIC_REGEX = "[a-zA-Z0-9]+"
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    private val log = LoggerFactory.getLogger(Generator::class.java)

    //server side has validations and corrects for censorship (ex. 'xX', '666', ...)
    fun generateRandomCensoredName(prefix: String = "", postfixLength: Int = DIGIT_COUNT): String {
        val randomizerCharPool = charPool.toMutableList()
        val randomNameBuilder = StringBuilder(prefix)
        randomNameBuilder.append((1..postfixLength)
            .map {
                val randomChar = randomizerCharPool[Random.nextInt(0, randomizerCharPool.size)]
                randomizerCharPool.remove(randomChar.toLowerCase())
                randomizerCharPool.remove(randomChar.toUpperCase())
                randomChar
            }
            .joinToString(""))
        return randomNameBuilder.toString()
    }

    fun generateRandomName(prefix: String, postfixLength: Int = DIGIT_COUNT): String {
        val randomizerCharPool = charPool.toMutableList()
        val randomNameBuilder = StringBuilder(prefix)
        randomNameBuilder.append((1..postfixLength)
            .map { randomizerCharPool[Random.nextInt(0, randomizerCharPool.size)] }
            .joinToString(""))
        return randomNameBuilder.toString()
    }

    fun randomString(): String {
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE).toString()
    }

    fun generatePhoneNumber(countryCodePrefix: String, postfixLength: Int = DIGIT_COUNT): String {
        val builder = StringBuilder(countryCodePrefix)
        for (i in 0 until postfixLength) builder.append(Random.nextInt(10))
        return builder.toString()
    }

    fun randomUid() = UUID.randomUUID().toString().replace("-", "")

    fun generateRandomImage(width: Int = 800, height: Int = 600): BufferedImage {
        val size = Dimension(width, height)
        //TODO generate random color
        val image = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB)
        for (x in 0 until size.width) {
            for (y in 0 until size.height) {
                image.setRGB(x, y, 0xff0000)
            }
        }
        return image
    }

    //TODO remove files after finish
    fun generateRandomImageFile(specificPrefix: String = "",
                                file: File = Paths.get(Constant.PATH_TO_ARTIFACTS, "thread-${Thread.currentThread().id}-time-${System.currentTimeMillis()}$specificPrefix.jpg").toFile(),
                                width: Int = 800, height: Int = 600): File {
        val image = generateRandomImage(width, height)
        if (!ImageIO.write(image, "BMP", file)) throw RuntimeException("File $file was not created.")
        return file.also { log.info("File $file was created") }
    }
}
