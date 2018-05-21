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

    /**
     * List last 10 Blocks in Chain
     */
    fun list()
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

    override fun list() {
        for (b in chain.takeLast(10)) {
            println("Last 10 Blocks:")
            println("Block: $b")
        }
    }

}

fun main(args: Array<String>) {
    val chain: Chain = DefaultChain()

    try {
        val genesis = Block.create("test", "0")
        if (chain.add(genesis)) {
            println("Genesis block added: $genesis")
            chain.list()
        }
    } catch (e: Exception) {
        println("Unable to add genesis block.")
    }
}