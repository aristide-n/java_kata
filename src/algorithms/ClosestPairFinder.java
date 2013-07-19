package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Arrays;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/16/13
 *
 * Strassen's algorithm for finding the closest pair of points in a array of points of the plane
 * http://books.google.com/books?id=NLngYyWFl_YC&lpg=PP1&ots=BwVsGG7kFb&pg=957#v=onepage&q&f=true
 * https://d396qusza40orc.cloudfront.net/algo1/slides%2Falgo-closest1.pdf
 * TODO: take out magic numbers
 */
public class ClosestPairFinder {
    // D
    public Point2D[] findClosestPair(Point2D[] points){
        Point2D[] closestPair;

        // presort the points array by X and Y
        Point2D[] sortedPointsByX = sortByX(points);
        Point2D[] sortedPointsByY = sortByY(points);

        // use the recursive method to find the closest pair
        closestPair = (Point2D[])findClosestPairInArray(points, sortedPointsByX, sortedPointsByY).get("pair");

        return closestPair;
    }


    //P   > TEST
    private HashMap<String, Object> findClosestPairInArray(Point2D[] unsortedArray,
                                                           Point2D[] arraySortedByX,
                                                           Point2D[] arraySortedByY){
        double bestClosestPairDistance = Double.MAX_VALUE;
        Point2D[] closestPair = new Point2D[2];
        HashMap<String, Object> result = new HashMap<String, Object>();

        // Base case
        if (unsortedArray.length <= 3){
            result = findByBruteForce(unsortedArray);
        }
        // Recursion
        else{
            // Split the unsorted array; the left split is longer when the array's length is uneven
            Point2D[] leftUnsortedArray = Arrays.copyOfRange(unsortedArray,
                                                             0,
                                                             (int)Math.ceil(unsortedArray.length/2.0));
            Point2D[] rightUnsortedArray = Arrays.copyOfRange(unsortedArray,
                                                               (int)Math.ceil(unsortedArray.length/2.0),
                                                               unsortedArray.length);

            // Sort the sub-arrays
            Point2D[] leftArraySortedByX = new Point2D[leftUnsortedArray.length];
            Point2D[] rightArraySortedByX = new Point2D[rightUnsortedArray.length];

            distributePresortedArray(arraySortedByX,
                                     leftUnsortedArray,
                                     leftArraySortedByX,
                                     rightArraySortedByX);


            Point2D[] leftArraySortedByY = new Point2D[leftUnsortedArray.length];
            Point2D[] rightArraySortedByY = new Point2D[rightUnsortedArray.length];

            distributePresortedArray(arraySortedByY,
                                     leftUnsortedArray,
                                     leftArraySortedByY,
                                     rightArraySortedByY);

            // Find the closest points in the sub-arrays
            HashMap<String, Object> leftResult = findClosestPairInArray(leftUnsortedArray,
                                                                        leftArraySortedByX,
                                                                        leftArraySortedByY);

            HashMap<String, Object> rightResult = findClosestPairInArray(rightUnsortedArray,
                                                                         rightArraySortedByX,
                                                                         rightArraySortedByY);

            // Find the closest split points

            // Set delta
            double leftDist = (Double)leftResult.get("distance");
            double rightDist = (Double)rightResult.get("distance");
            if (leftDist < rightDist){
                bestClosestPairDistance = leftDist;
                closestPair = (Point2D[])leftResult.get("pair");
            }
            else {
                bestClosestPairDistance = rightDist;
                closestPair = (Point2D[])rightResult.get("pair");
            }

            HashMap<String, Object> splitResult = findClosestSplitPairInArray(arraySortedByY,
                                                                              leftArraySortedByX,
                                                                              bestClosestPairDistance);

            // Set the output to the best pair
            double splitDist = (Double)splitResult.get("distance");

            if (splitDist < bestClosestPairDistance){
                bestClosestPairDistance = splitDist;
                closestPair = (Point2D[])splitResult.get("pair");
            }

            result.put("distance", bestClosestPairDistance);
            result.put("pair", closestPair);
        }

        return result;
    }


    //! P
    private HashMap<String, Object> findClosestSplitPairInArray(Point2D[] sortedArrayByY,
                                                                Point2D[] leftSubArraySortedByX,
                                                                double delta){
        double closestPairDistance = Double.MAX_VALUE;
        Point2D[] closestPair = new Point2D[2];
        HashMap<String, Object> result = new HashMap<String, Object>();

        // The line that divides the plane in two has x coordinate equal to the last point of leftSubArraySortedByX
        // The range of X coordinates of the split pair is 2 * delta wide on either side of the line
        double centerX = leftSubArraySortedByX[leftSubArraySortedByX.length-1].getX();
        double maxX = centerX + delta;
        double minX = centerX - delta;

        // make list of points within the range
        ArrayList<Point2D> pointsInRange = new ArrayList<Point2D>();

        for (int i = 0; i < sortedArrayByY.length; i++){
            double x = sortedArrayByY[i].getX();
            if(minX <= x && x <= maxX){
                pointsInRange.add(sortedArrayByY[i]);
            }
        }
        // Base case
        if (pointsInRange.size() <= 8){
            Point2D[] points = new Point2D[pointsInRange.size()];
            result = findByBruteForce(pointsInRange.toArray(points));
        }
        // If there is at least 8 points in the range, seek the closest pair
        for (int i = 0; i < (pointsInRange.size()-7); i++){
            for (int j = 0; j < 8; j++){
                double distance = pointsInRange.get(i).distance(pointsInRange.get(i+j));
                if (distance < closestPairDistance){
                    closestPairDistance = distance;
                    closestPair[0] = pointsInRange.get(i);
                    closestPair[1] = pointsInRange.get(i+j);
                }
            }
        }

        result.put("distance", closestPairDistance);
        result.put("pair", closestPair);

        return result;
    }


    //D
    private Point2D[] sortByX(Point2D[] unsortedArray){
        // First copy the unsorted array
        Point2D[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // sort the copy by X
        Arrays.sort(sortedArray, new Comparator<Point2D>(){
            @Override
            public int compare(Point2D point1, Point2D point2){
                return Double.compare(point1.getX(), point2.getX());
            }
        });

        return sortedArray;
    }


    //D
    private Point2D[] sortByY(Point2D[] unsortedArray){
        // First copy the unsorted array
        Point2D[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // sort the copy by Y
        Arrays.sort(sortedArray, new Comparator<Point2D>(){
            @Override
            public int compare(Point2D point1, Point2D point2){
                return Double.compare(point1.getY(), point2.getY());
            }
        });

        return sortedArray;
    }


    //D
    private void distributePresortedArray(Point2D[] sortedArray,
                                          Point2D[] leftUnsortedArray,
                                          Point2D[] leftSortedArray,
                                          Point2D[] righttSortedArray){
        int iLeft = 0;
        int iRight = 0;

        for (int i = 0; i < sortedArray.length; i++){
            if (Arrays.asList(leftUnsortedArray).contains(sortedArray[i])){
                leftSortedArray[iLeft] = sortedArray[i];
                iLeft++;
            }
            else{
                righttSortedArray[iRight] = sortedArray[i];
                iRight++;
            }
        }
    }


    //D
    private HashMap<String, Object> findByBruteForce(Point2D[] smallArray){
        double closestPairDistance = Double.MAX_VALUE;
        Point2D[] closestPair = new Point2D[2];
        HashMap<String, Object> result = new HashMap<String, Object>();

        for (int i = 0; i < smallArray.length; i++){
            for (int j = i+1; j < smallArray.length; j++){
                double distance = smallArray[i].distance(smallArray[j]);
                if (distance < closestPairDistance){
                    closestPairDistance = distance;
                    closestPair[0] = smallArray[i];
                    closestPair[1] = smallArray[j];
                }
            }
        }

        result.put("distance", closestPairDistance);
        result.put("pair", closestPair);

        return result;
    }
}
