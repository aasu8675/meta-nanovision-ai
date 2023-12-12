# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/aasu8675/gpio_test;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "c1988311dd765959c8c1b73fccb800994bb06022"

S = "${WORKDIR}/git/"

FILES_${PN} += "${bindir}/gpio_test/Makefile"
FILES_${PN} += "${bindir}/gpio_test/test_application.c"
FILES_${PN} += "${bindir}/gpio_test/test_application"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}/gpio_test
    install -m 0755 ${S}/Makefile ${D}${bindir}/gpio_test
    install -m 0755 ${S}/test_application.c ${D}${bindir}/gpio_test
    install -m 0755 ${B}/test_application ${D}${bindir}/gpio_test
}
