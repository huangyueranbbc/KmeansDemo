package com.hyr.kmeans;

/*******************************************************************************
 * @date 2018-02-09 下午 1:58
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class Point {

    private Float x;     // x 轴
    private Float y;    // y 轴

    public Point(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * 计算距离
     *
     * @param centroid 质心点
     * @param type     1:L1 2:L2 3:余弦相似度
     * @return
     */
    public Double calculateDistance(Point centroid, int type) {
        Double result = null;
        switch (type) {
            case 1: // 欧氏距离
                result = calcDistance(centroid);
                break;
            case 2:
                ;
                break;
            case 3:
                ;
                break;
        }
        return result;
    }

    private double calcDistance(Point centroid) {
        return Math.sqrt(Math.pow((centroid.getX() - getX()), 2) + Math.pow((centroid.getY() - getY()), 2));
    }

    @Override
    public boolean equals(Object obj) {
        Point other = (Point) obj;
        if (getX().equals(other.getX()) && getY().equals(other.getY())) {
            return true;
        }
        return false;
    }
}
