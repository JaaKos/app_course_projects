import QtQuick

Rectangle {
    id: buttonbase
    signal clicked
    property string text: "Label"
    property color buttonColor: "#4d4d4d"
    property int buttonWidth: 128
    property int buttonHeight: 64

    width: buttonWidth
    height: buttonHeight
    color: buttonColor
    border.width: 2

    property bool toggle: false
    Text {
        id: label
        text: buttonbase.text
        anchors.fill: parent
        font.pixelSize: 12
        horizontalAlignment: Text.AlignHCenter
        verticalAlignment: Text.AlignVCenter
    }

    MouseArea {
        id: mouseArea
        anchors.fill: parent

        Connections {
            target: mouseArea
            onPressed: {
                buttonbase.color.hslLightness *= 0.8
                buttonbase.scale = 0.95
            }
        }

        Connections {
            target: mouseArea
            onReleased: {
                buttonbase.toggle = !buttonbase.toggle

                if (buttonbase.toggle) {
                    buttonbase.color.hslLightness = 0.5
                } else {
                    buttonbase.color.hslLightness = 0.3
                }
                buttonbase.scale = 1
            }
        }

        Connections {
            target: mouseArea
            onClicked: buttonbase.clicked()
        }
    }
}
