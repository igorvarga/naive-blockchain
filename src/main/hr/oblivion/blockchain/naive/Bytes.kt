package main.hr.oblivion.blockchain.naive

import javax.xml.bind.DatatypeConverter

fun ByteArray.toHexString(): String = DatatypeConverter.printHexBinary(this)