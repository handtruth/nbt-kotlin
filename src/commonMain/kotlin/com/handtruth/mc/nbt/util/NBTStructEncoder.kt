package com.handtruth.mc.nbt.util

import com.handtruth.mc.nbt.tags.CompoundTag
import com.handtruth.mc.nbt.tags.Tag
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.modules.SerialModule

internal class NBTStructEncoder(context: SerialModule, private val parent: NBTEncoder) : NBTCompositeEncoder(context) {
    private val result: MutableMap<String, Tag<*>> = hashMapOf()

    override fun endStructure(descriptor: SerialDescriptor) {
        parent.tag = CompoundTag(result)
    }

    override fun <T: Any> placeTag(descriptor: SerialDescriptor, index: Int, tag: Tag<T>) {
        val name = descriptor.getElementName(index)
        result[name] = tag
    }
}
