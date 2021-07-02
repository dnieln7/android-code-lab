package com.dnieln7.data_store.data.preferences.proto.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.dnieln7.data_store.data.model.PetProto
import com.dnieln7.data_store.data.model.PetProto.parseFrom
import java.io.InputStream
import java.io.OutputStream

class PetSerializer : Serializer<PetProto> {
    override val defaultValue: PetProto = PetProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): PetProto {
        try {
            return parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: PetProto, output: OutputStream) {
        t.writeTo(output)
    }
}