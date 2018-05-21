package main.hr.oblivion.blockchain.naive

import java.util.Date

data class Block private constructor(val hash: String, val data: String, val previousHash: String, val timeStamp: Long) {

    override fun toString(): String {
        return "Block(hash='$hash', data='$data', previousHash='$previousHash', timeStamp=$timeStamp)"
    }

    companion object {
        fun create(data: String, previousHash: String):Block {
            val t = Date().time
            return Block((previousHash + t.toString() + data).sha256(), data, previousHash, t)
        }
    }

}