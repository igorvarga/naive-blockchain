package main.hr.oblivion.blockchain.naive

interface Chain {

    /**
     * Add Block to the Chain
     */
    fun add(block: Block): Boolean

    /**
     * Validate Chain
     */
    fun valid(): Boolean

    /**
     * Return last Block from the Chain
     */
    fun last(): Block
}

class DefaultChain : Chain {

    private val chain:MutableList<Block> = mutableListOf()

    override fun valid(): Boolean {
        return false
    }

    override fun add(block: Block): Boolean {
        try {
            chain.add(block)
            return true
        } catch (e: Exception) {
            println(e)
        }

        return false
    }

    override fun last(): Block {
        return chain.last()
    }

}

fun main(args: Array<String>) {
    val chain: Chain = DefaultChain()

    try {
        val genesis = Block.create("test", "0")
        if (chain.add(genesis)) {
            println("Genesis block added: $genesis")
        }
    } catch (e: Exception) {
        println("Unable to add genesis block.")
    }
}