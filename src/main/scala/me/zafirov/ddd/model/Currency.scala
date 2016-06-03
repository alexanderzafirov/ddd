package me.zafirov.ddd.model

sealed trait Currency
case object USD extends Currency
case object AUD extends Currency
case object EUR extends Currency
case object INR extends Currency
