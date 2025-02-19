

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Trafficlights

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    Column {
        id: column
        x: 194
        y: 55
        width: 166
        height: 309
        spacing: -92

        Rectangle {
            id: rectangle1
            x: 0
            y: -50
            width: 200
            height: 200
            color: "#990000"
            border.width: 2
            scale: 0.5
        }

        Rectangle {
            id: rectangle2
            y: 63
            width: 200
            height: 200
            color: "#999900"
            border.width: 2
            scale: 0.5
        }

        Rectangle {
            id: rectangle3
            y: 176
            width: 200
            height: 200
            color: "#009900"
            border.color: "#000000"
            border.width: 2
            scale: 0.5
        }
    }

    Timer {
        id: timer
        property int state: 0
        repeat: true
        running: false
    }

    Connections {
        target: timer
        onTriggered: {
            rectangle1.color.hslLightness = 0.3
            rectangle2.color.hslLightness = 0.3
            rectangle3.color.hslLightness = 0.3
            timer.state++

            if (timer.state == 1) {
                rectangle1.color.hslLightness = 0.5
            } else if (timer.state == 2) {
                rectangle1.color.hslLightness = 0.5
                rectangle2.color.hslLightness = 0.5
            } else if (timer.state == 3) {
                rectangle3.color.hslLightness = 0.5
            } else if (timer.state == 4) {
                rectangle2.color.hslLightness = 0.5
                timer.state = 0
            }
        }
    }

    CustomButton {
        id: customButton
        x: 430
        y: 165
        color: "#998e00"
        text: "Start/Stop"
        buttonColor: "#fff352"
        property bool running: false

        Connections {
            target: customButton
            onClicked: {
                customButton.running = !customButton.running
                if (customButton.running) {
                    customButton.color.hslLightness = 0.5
                    timer.start()
                } else {
                    customButton.color.hslLightness = 0.3
                    timer.stop()
                }
            }
        }
    }
    states: [
        State {
            name: "clicked"
        }
    ]
}
