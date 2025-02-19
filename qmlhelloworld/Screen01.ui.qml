

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Helloworld
import Qt.SafeRenderer

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height
    color: "#dddddd"

    Button {
        id: button
        width: 198
        height: 94
        text: qsTr("Press me")
        anchors.verticalCenter: parent.verticalCenter
        autoRepeat: false
        checked: false
        autoExclusive: false
        display: AbstractButton.TextOnly
        checkable: true
        anchors.horizontalCenter: parent.horizontalCenter

        Connections {
            target: button
            enabled: true
            onToggled: {
                if (button.checked) {
                    rectangle.color = "#bbbbbb"
                    rectangle.rotation = 20
                    _text.text = "Button pressed"
                }
                if (!button.checked) {
                    rectangle.color = '#dddddd'
                    rectangle.rotation = 0
                    _text.text = "Hello, world!"
                }
            }
        }
    }

    Text {
        id: _text
        x: 885
        y: 365
        width: 151
        height: 36
        text: qsTr("Hello, World!")
        anchors.top: parent.top
        anchors.bottom: parent.bottom
        anchors.topMargin: 264
        anchors.bottomMargin: 772
        font.pixelSize: 24
        anchors.horizontalCenterOffset: 8
        anchors.horizontalCenter: parent.horizontalCenter
    }
    states: [
        State {
            name: "clicked"
            when: button.checked
        }
    ]
}
