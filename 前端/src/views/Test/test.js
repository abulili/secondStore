// int pnpoly (int nvert, float *vertx, float *verty, float testx, float testy) {
//     int i, j, c = 0;
//     for (i = 0, j = nvert-1; i < nvert; j = i++) {
//         if ( ( (verty[i]>testy) != (verty[j]>testy) )
//         && (testx <= (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
//             c = !c;
//     }
//         return c;
// }
// function pointInPolygon(point, polygon) {
//     let inside = false;
//     const x = point[0],
//         y = point[1];
//     const vertices = polygon.slice(); // 复制多边形的顶点数组
//     const numVertices = vertices.length;

//     for (let i = 0, j = numVertices - 1; i < numVertices; j = i++) {
//         const xi = vertices[i][0],
//             yi = vertices[i][1];
//         const xj = vertices[j][0],
//             yj = vertices[j][1];

//         const intersect = ((yi > y) !== (yj > y)) &&
//             (x < ((xj - xi) * (y - yi) / (yj - yi) + xi));
//         // 是否与多边形顶点重合
//         const intersect1 = (xi == x && yi == y) || (xj == x && yj == y);
//         if (intersect || intersect1) {
//             inside = !inside;
//         }
//     }

//     return inside;
// }

// console.log(pointInPolygon([1, 1.1], [[0, 0], [0, 1.1], [2, 1.1], [2, 0]]))


// function isCollineation(Point a, Point b, Point c, Point d){ //是否平行

//     if ( (b.x - a.x) * (d.y - c.y) - (b.y - a.y) * (d.x - c.x) ){
//         return 0;
//     }else{
//         return 1 ;
//     }
// }

// function checkCross(Point a, Point b, Point c, Point d ){

//     if (isCollineation(a, b, c, d)) {

//         return false;
//     }

//     if ( mulCross(c, d, a) * mulCross(c, d, b) <= 0 ) {
//         if ( mulCross(a, b, c) * mulCross(a, b, d) <= 0 && mulCross(a, b, d)!= 0 ) {
//             return true;
//         }
//     }

//     return false;

// }
// function checkInRectOther(p , pt , pNum){

//     let basicP = Point(pt->x,1000);
//     int crossNum = 0;

//     for (int i = 1; i<pNum; i++) {
//         if (checkCross(*pt, basicP, p[i-1], p[i])) {
//             crossNum++;
//         }
//     }

//     return crossNum%2;
// }

// checkInRectOther([1, 1.1], [[0, 0], [0, 1.1], [2, 1.1], [2, 0]],4)
function IsPointOnLine(x, y, cx1, cy1, cx2, cy2) {
    // let temp = (cx1 - x) * (cy2 - y) - (cx2 - x) * (cy1 - y)
    if (((x - cx1) * (x - cx2) <= 0) && ((y - cy1) * (y - cy2) <= 0)) {
        return true
    }
    return false
}
// 两线段是否相交
function IsIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y) {
    let d = (cx2 - cx1) * (linePoint2y - linePoint1y) - (cy2 - cy1) * (linePoint2x - linePoint1x);
    if (d != 0) {
        let r = ((cy1 - linePoint1y) * (linePoint2x - linePoint1x) - (cx1 - linePoint1x) * (linePoint2y - linePoint1y)) / d;
        let s = ((cy1 - linePoint1y) * (cx2 - cx1) - (cx1 - linePoint1x) * (cy2 - cy1)) / d;
        if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
            return true
        }
    }
    return false
}
function PointCheck(p, arr) {
    let count = false

    let minX = 180.0
    for (let i = 0; i < arr.legth; ++i) {
        if (arr[i][0] < minX) {
            minX = arr[i][0]
        }
    }

    let px = p[0]
    let py = p[1]

    let linePoint1x = px
    let linePoint1y = py
    let linePoint2x = minX - 0.1
    let linePoint2y = py

    for (let i = 0; i < arr.length - 1; ++i) {
        let cx1 = arr[i][0]
        let cy1 = arr[i][1]
        let cx2 = arr[i + 1][0]
        let cy2 = arr[i + 1][1]

        let k1;
        let k2;
        if (i > 0) {
            k1 = (arr[i - 1][1] - cy1) / (arr[i - 1][0] - cx1)
            k2 = (cy2 - cy1) / (cx2 - cx1)
        }

        if (k1 != k2 && IsPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
            return true;
        }
        // 假设是两个三角形纠缠的中间，要确保他们的斜率是不一样的，一样的就需要反方向判定了
        // 多边形的一个顶点在射线上，且该顶点是上顶点(y值较高)，算一个交点
        if (k1 != k2 && IsPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
            if (cy1 > cy2) {
                count = !count;
            }
        }
        // 多边形的一个顶点在射线上，且该顶点是上顶点(y值较高)，算一个交点
        else if (k1 != k2 && IsPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
            if (cy2 > cy1) {
                count = !count;
            }
        }
        // 已经排除平行的情况，其他相交的都算一个交点
        else if (k1 != k2 && IsIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
            count = !count;
        }

    }
    return count
}
// console.log(PointCheck([1, 1.1], [[0, 0], [0, 1.1], [2, 1.1], [2, 0]]))
// console.log(PointCheck([0, 1], [[0, 2], [2, 2], [1, 1], [0, 0], [2, 0]]))

// let num = [[103.76209695801786, 29.56226087537925], [103.75376068288803, 29.562386538161334], [103.75864136268984, 29.566402397460624], [103.76209695801786, 29.56226087537925]]
// console.log(num.toString())





// 然后将每对坐标转换为数组，并将它们收集到一个新数组中
const str = "103.76209695801786,29.56226087537925,103.75376068288803,29.562386538161334,103.75864136268984,29.566402397460624,103.76209695801786,29.56226087537925";
const pairs = str.split(',');
const coordinates = [];
for (let i = 0; i < pairs.length; i += 2) {
    const longitude = parseFloat(pairs[i]);
    const latitude = parseFloat(pairs[i + 1]);
    coordinates.push([longitude, latitude]);
}
console.log(coordinates);