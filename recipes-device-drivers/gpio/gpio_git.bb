# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/aasu8675/Jetson_Nano_GPIO_Driver;protocol=ssh;branch=main \
file://S98lddmodules-gpio-start-stop.sh"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "6371c27d2e0f18c5997c8df483fa3b998bc8774c"

S = "${WORKDIR}/git/"

inherit module

MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE:append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "S98lddmodules-gpio-start-stop"

FILES:${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/aesdgpio_load"
FILES:${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/aesdgpio_unload"
FILES:${PN} += "${bindir}/Makefile"
FILES:${PN} += "${bindir}/gpio_driver.c"
FILES:${PN} += "${sysconfdir}/*"

do_configure () {
	:
}

do_compile () {
	unset LDFLAGS
	oe_runmake LD="${KERNEL_LD}"
}

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
    	install -d "${D}${base_libdir}/modules/${KERNEL_VERSION}/"
	install -m 0755 ${S}/aesdgpio_load "${D}${base_libdir}/modules/${KERNEL_VERSION}/"
     	install -m 0755 ${S}/aesdgpio_unload "${D}${base_libdir}/modules/${KERNEL_VERSION}/"
	install -m 0755 ${S}/Makefile ${D}${bindir}/
	install -m 0755 ${S}/gpio_driver.c ${D}${bindir}/
	install -m 0755 ${WORKDIR}/S98lddmodules-gpio-start-stop.sh ${D}${sysconfdir}/init.d
    	install -m 0755 "${S}/gpio_driver.ko" "${D}${base_libdir}/modules/${KERNEL_VERSION}/"
}
