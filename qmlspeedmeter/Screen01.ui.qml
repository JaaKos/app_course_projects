

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Speedometer

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height
    gradient: Gradient {
        GradientStop {
            position: 0
            color: "#ffffff"
        }

        GradientStop {
            position: 1
            color: "#000000"
        }
        orientation: Gradient.Vertical
    }


    Image {
        id: image
        x: 155
        y: 31
        width: 336
        height: 326
        source: "images/gauge.png"
        fillMode: Image.PreserveAspectFit

        Image {
            id: image1
            x: 118
            y: 39
            width: 99
            height: 130
            source: "images/needlered.png"
            z: 1
            rotation: -130
            property int speed: 0
            transformOrigin: Item.Bottom
            fillMode: Image.PreserveAspectFit

            Connections {
                target: image1
                onSpeedChanged: {
                    if (image1.speed < 0)
                        image1.speed = 0
                    else if (image1.speed > 260)
                        image1.speed = 260
                    image1.rotation = (image1.speed - 130)
                }
            }
        }
    }

    Button {
        id: button
        x: 195
        y: 388
        text: qsTr("Gas")

        Connections {
            target: button
            onPressed: {
                idleTimer.stop()
                gasTimer.start()
            }
        }

        Connections {
            target: button
            onReleased: {
                gasTimer.stop()
                idleTimer.start()
            }
        }
    }

    Timer {
        id: gasTimer
        repeat: true
        interval: 20
    }

    Timer {
        id: idleTimer
        triggeredOnStart: true
        repeat: true
        interval: 80
    }

    Timer {
        id: brakeTimer
        repeat: true
        interval: 20
    }

    Connections {
        target: gasTimer
        onTriggered: image1.speed += 1
    }

    Connections {
        target: idleTimer
        onTriggered: image1.speed -= 1
    }

    Connections {
        target: brakeTimer
        onTriggered: image1.speed -= 3
    }

    Button {
        id: button1
        x: 342
        y: 386
        text: qsTr("Brake")

        Connections {
            target: button1
            onPressed: {
                idleTimer.stop()
                brakeTimer.start()
            }
        }

        Connections {
            target: button1
            onReleased: {
                brakeTimer.stop()
                idleTimer.start()
            }
        }
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
