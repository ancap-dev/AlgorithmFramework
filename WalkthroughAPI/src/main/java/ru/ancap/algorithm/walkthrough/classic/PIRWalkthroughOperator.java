package ru.ancap.algorithm.walkthrough.classic;

import org.locationtech.jts.geom.*;

import java.util.function.Function;

/**
 * PolygonIntersectsRegionWalkthroughOperator
 */
public class PIRWalkthroughOperator<NODE> extends RegionWalkthroughOperator<NODE> {

    public PIRWalkthroughOperator(java.awt.Polygon inputRegion, Function<NODE, java.awt.Polygon> polygonFunction) {
        super((node) -> {
            java.awt.Polygon inputPolygon = polygonFunction.apply(node);
            
            GeometryFactory geometryFactory = new GeometryFactory();
            Polygon region = jtsPolygonOfAwt(geometryFactory, inputRegion);
            Polygon polygon = jtsPolygonOfAwt(geometryFactory, inputPolygon);

            return region.intersection(polygon).relate(geometryFactory.createPoint()).isIntersects();
        });
    }

    private static Polygon jtsPolygonOfAwt(GeometryFactory geometryFactory, java.awt.Polygon awtRegion) {
        Coordinate[] coordinates = new Coordinate[awtRegion.npoints + 1];
        for (int i = 0; i < awtRegion.npoints; i++) coordinates[i] = new Coordinate(awtRegion.xpoints[i], awtRegion.ypoints[i]);
        coordinates[coordinates.length-1] = new Coordinate(awtRegion.xpoints[0], awtRegion.ypoints[0]);
        return geometryFactory.createPolygon(coordinates);
    }

}
