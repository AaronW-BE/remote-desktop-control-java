package utils;

import java.awt.*;

public class ScaleUtils {
    /**
     * 获取指定比例的位置坐标
     * @param point 当前坐标点
     * @param src 当前分辨率
     * @param dst 目标分辨率
     * @return Point
     */
    public static Point getPoint(Point point, Dimension src, Dimension dst) {
        int sx = point.x, sy = point.y;
        int dx = (int) sx * dst.width / src.width;
        int dy = (int) sy * dst.height / src.height;
        return new Point(dx, dy);
    }
}
