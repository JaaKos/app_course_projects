import QtQuick

Rectangle {
    id: buttonbase
    signal clicked
    property string text: "Label"
    property color buttonColor: "#cccccc"
    property int buttonWidth: 128
    property int buttonHeight: 64

    color: buttonbase.buttonColor
    width: buttonbase.buttonWidth
    height: buttonbase.buttonHeight

    border.color: "#000000"
    border.width: 2
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
                buttonbase.color = buttonbase.buttonColor
                buttonbase.scale = 1
            }
        }

        Connections {
            target: mouseArea
            onClicked: buttonbase.clicked()
        }
    }
}
