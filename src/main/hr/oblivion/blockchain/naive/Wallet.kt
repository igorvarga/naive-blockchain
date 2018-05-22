package main.hr.oblivion.blockchain.naive

import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.spec.ECGenParameterSpec


data class Wallet private constructor(private val privateKey: PrivateKey, private val publicKey: PublicKey) {

    companion object {
        fun create():Wallet {
            val keygen = KeyPairGenerator.getInstance("ECDSA")
            val random = SecureRandom.getInstance("SHA1PRNG")
            val ecspec = ECGenParameterSpec("prime192v1")
            keygen.initialize(ecspec, random)

            val keyPair = keygen.generateKeyPair()

            return Wallet(keyPair.private, keyPair.public)
        }
    }
}