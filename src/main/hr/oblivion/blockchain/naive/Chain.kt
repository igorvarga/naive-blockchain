package main.hr.oblivion.blockchain.naive

const val MINING_DIFFICULTY: Int = 5

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

    private val chain: MutableList<Block> = mutableListOf()

    override fun valid(): Boolean {

        when (chain.size) {
            0 -> return false
            1 -> return "0" == chain.last().previousHash
        }

        for (i in 1 until chain.size) {
            val current = chain[i]
            val previous = chain[i-1]
            if (current.previousHash != previous.hash
                    || current.hash != current.hash()
                    || current.hash.take(MINING_DIFFICULTY) != "0".repeat(MINING_DIFFICULTY)) return false
        }

        return true
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
        println("Last 10 Blocks:")

        for (b in chain.takeLast(10)) {
            println("Block: $b")
        }
    }

}

fun main(args: Array<String>) {

    val chain: Chain = DefaultChain()

    val genesis = Block.create("Genesis block", "0")

    chain.add(genesis)

    println("Genesis block added: $genesis")

    genesis.mine(MINING_DIFFICULTY)

    val second = Block.create("Second block", genesis.hash)

    chain.add(second)

    second.mine(MINING_DIFFICULTY)

    val third = Block.create("Third block", second.hash)

    chain.add(third)

    third.mine(MINING_DIFFICULTY)

    chain.list()

    println("Chain is valid: ${chain.valid()}")
}