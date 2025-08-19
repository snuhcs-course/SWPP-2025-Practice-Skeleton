package com.example.abstractfactoryexample

fun main(args: Array<String>) {
    val company = "Apple"
    val phone: Phone
    val tablet: Tablet
    val laptop: Laptop

    if (company == "Apple") {
        phone = iPhone15()
        tablet = iPad()
        laptop = MacBook()
    } else {
        phone = GalaxyS23()
        tablet = GalaxyTab()
        laptop = GalaxyBook()
    }

    phone.call()
    tablet.touch()
    laptop.typing()
}
