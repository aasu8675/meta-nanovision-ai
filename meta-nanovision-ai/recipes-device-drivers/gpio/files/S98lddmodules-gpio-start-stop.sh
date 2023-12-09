#!/bin/sh

case "$1" in
    start)
        echo "Starting gpio"
        /usr/bin/aesdgpio_load
        ;;
    stop)
        echo "Stopping gpio"
        /usr/bin/aesdgpio_unload
        ;;
    *)
        echo "Usage: $0 [start|stop]"
    exit 1
esac
exit 0
