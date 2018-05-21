package main.hr.oblivion.blockchain.naive

import java.util.Date

data class Block private constructor(var hash: String , val data: String, val previousHash: String, val timeStamp: Long, private var nonce: Int = 0) {

    override fun toString(): String {
        return "Block(hash='$hash', data='$data', previousHash='$previousHash', timeStamp=$timeStamp, nonce=$nonce)"
    }

    companion object {
        fun create(data: String, previousHash: String): Block {
            val timeStamp = Date().time
            val block = Block("0", data, previousHash, timeStamp)
            block.hash = Block.hash(block)
            return block
        }

        fun hash(block: Block): String {
            return (block.previousHash + block.timeStamp.toString() + block.nonce.toString() + block.data).sha256()
        }

        fun mine(block: Block, difficulty: Int) {
            val mining = "0".repeat(difficulty)

            while (mining != block.hash.take(difficulty)) {
                block.nonce++
                block.hash = Block.hash(block)
            }

            println("Block ${block.hash} mined")
        }
    }

}