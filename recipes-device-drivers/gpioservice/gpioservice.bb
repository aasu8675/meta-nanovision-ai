LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "gpio_buzzer_driver_boot.service"

SRC_URI_append = "file://gpio_buzzer_driver_boot.service"
FILES_${PN} += "${systemd_unitdir}/system/gpio_buzzer_driver_boot.service"

do_install_append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/gpio_buzzer_driver_boot.service ${D}/${systemd_unitdir}/system
}
