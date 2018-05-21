package main.hr.oblivion.blockchain.naive

interface Chain {
    fun add(block: Block): Boolean
    fun valid(): Boolean
}

class DefaultChain : Chain {

    private val chain:MutableMap<String, Block> = HashMap()

    override fun valid(): Boolean {
        return false
    }

    override fun add(block: Block): Boolean {
        try {
            chain[block.hash] = block
            return true
        } catch (e: Exception) {
            println(e)
        }

        return false
    }
}

fun main(args: Array<String>) {
    val chain: Chain = DefaultChain()

    try {
        val genesis = Block.create("test", "0")
        chain.add(genesis)
        println("Genesis block added: $genesis")
    } catch (e: Exception) {
        println("Unable to add genesis block.")
    }
}