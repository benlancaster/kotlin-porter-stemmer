package com.alexzolotov.nlp

import java.io.BufferedReader
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.test.assertEquals

class StemmerIT {
    private val stemmer = Stemmer()

    @Test(dataProvider = "vocab")
    fun vocab(input: String, expected: String) {
        assertEquals(expected, stemmer.stem(input))
    }

    @DataProvider(name = "vocab", parallel = true)
    fun provideVocab(): Iterator<Array<Any?>> {
        val inputData = resourceReader("/vocab_in.txt")
        val expectedData = resourceReader("/vocab_out.txt")
        return inputData.zip(expectedData).map { arrayOf<Any?>(it.first, it.second) }.iterator()
    }

    @Test(dataProvider = "masculine")
    fun masculine(input: String, expected: String) {
        assertEquals(expected, stemmer.stem(input))
    }

    @DataProvider(name = "masculine", parallel = true)
    fun provideMasculine(): Iterator<Array<Any?>> {
        val inputData = resourceReader("/masc_in.txt")
        val expectedData = resourceReader("/masc_out.txt")
        return inputData.zip(expectedData).map { arrayOf<Any?>(it.first, it.second) }.iterator()
    }

    @Test(dataProvider = "feminine")
    fun feminine(input: String, expected: String) {
        assertEquals(expected, stemmer.stem(input))
    }

    @DataProvider(name = "feminine", parallel = true)
    fun provideFeminine(): Iterator<Array<Any?>> {
        val inputData = resourceReader("/fem_in.txt")
        val expectedData = resourceReader("/fem_out.txt")
        return inputData.zip(expectedData).map { arrayOf<Any?>(it.first, it.second) }.iterator()
    }

    private fun resourceReader(resourceName: String): Sequence<String> {
        return this.javaClass.getResourceAsStream(resourceName)!!.reader().buffered().lineSequence()
    }
}