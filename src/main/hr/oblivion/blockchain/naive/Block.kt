package main.hr.oblivion.blockchain.naive

import java.util.Date

data class Block private constructor(var hash: String, val data: String, val previousHash: String, val timeStamp: Long, internal var nonce: Int = 0) {

    override fun toString(): String {
        return "Block(hash='$hash', data='$data', previousHash='$previousHash', timeStamp=$timeStamp, nonce=$nonce)"
    }

    companion object {
        fun create(data: String, previousHash: String): Block {
            val timeStamp = Date().time
            val block = Block("0", data, previousHash, timeStamp)
            block.hash()
            return block
        }

    }

}

fun Block.hash(): String {
    this.hash = (this.previousHash + this.timeStamp.toString() + this.nonce.toString() + this.data).sha256()
    return this.hash
}

fun Block.mine(difficulty: Int) {
    val mining = "0".repeat(difficulty)

    while (mining != this.hash.take(difficulty)) {
        this.nonce++
        this.hash()
    }

    println("Block ${this.hash} mined")
}