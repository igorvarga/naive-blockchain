package main.hr.oblivion.blockchain.naive

import java.security.MessageDigest

fun String.sha256() = MessageDigest.getInstance("SHA-256").digest(this.toByteArray(Charsets.UTF_8)).toHexString()