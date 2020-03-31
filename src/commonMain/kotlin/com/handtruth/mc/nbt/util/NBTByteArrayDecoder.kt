package com.handtruth.mc.nbt.util

import com.handtruth.mc.nbt.tags.ByteArrayTag
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.UpdateMode
import kotlinx.serialization.modules.SerialModule

internal class NBTByteArrayDecoder(
    private val tag: ByteArrayTag,
    context: SerialModule,
    updateMode: UpdateMode
) : NBTIndexedDecoder(context, updateMode) {
    override val size = tag.value.size

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        return tag.value[index].toInt() != 0
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        return tag.value[index]
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        return tag.value[index].toChar()
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        return tag.value[index].toDouble()
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        return tag.value[index].toFloat()
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        return tag.value[index].toInt()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        return tag.value[index].toLong()
    }

    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>
    ): T? {
        return if (index >= size)
            null
        else
            decodeSerializableElement(descriptor, index, deserializer)
    }

    override fun <T> decodeNonPrimitiveElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>
    ) = throw NBTException("failed to treat ByteArray element as ${deserializer.descriptor.kind} type")

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        return tag.value[index].toShort()
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        throw NBTException("failed to cast byte to string")
    }

    override fun decodeUnitElement(descriptor: SerialDescriptor, index: Int) {
        throw NBTException("failed to cast byte to Unit type")
    }
}