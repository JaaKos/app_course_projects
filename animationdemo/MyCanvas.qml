import QtQuick

Canvas {
    id: myCanvas
    width: 300
    height: 200

    property real ballX: -1
    property real ballY: -1
    property real speedX: 0
    property real speedY: 0
    property real ballSize: 20
    property color ballColor: "green"

    onPaint: {
        var ctx = getContext("2d");
        ctx.clearRect(0, 0, width, height);

        if (ballX > 0 && ballY > 0) {
            ctx.fillStyle = ballColor;
            ctx.beginPath();
            ctx.arc(ballX, ballY, ballSize, 0, 2 * Math.PI);
            ctx.fill();
        }
    }

    MouseArea {
        anchors.fill: parent
        onClicked: (mouse) => {
            ballX = mouse.x;
            ballY = mouse.y;
            ballColor.hslHue = Math.random()
            speedX = (Math.random() - 0.5) * 10;
            speedY = (Math.random() - 0.5) * 10;
            requestPaint();
            updateTimer.start()
        }
    }

    Timer {
        id: updateTimer
        interval: 10
        running: false
        repeat: true
        onTriggered: {
            while (ballX + speedX < 20 || ballX + speedX > 280) {
                speedX = (Math.random() - 0.5) * 10;
            }
            while (ballY + speedY < 20 || ballY + speedY > 180) {
                speedY = (Math.random() - 0.5) * 10;
            }

            ballX += speedX
            ballY += speedY
            myCanvas.requestPaint()
        }
    }
}
