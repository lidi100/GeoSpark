/*
 * FILE: UdfRegistrator.scala
 * Copyright (c) 2015 - 2018 GeoSpark Development Team
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.datasyslab.geosparksql.UDF

import org.apache.spark.sql.catalyst.analysis.FunctionRegistry
import org.apache.spark.sql.geosparksql.expressions._
import org.apache.spark.sql.{SQLContext, SparkSession}

object UdfRegistrator {
  def resigterConstructors(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.registerFunction("ST_PointFromText", ST_PointFromText)
    FunctionRegistry.builtin.registerFunction("ST_PolygonFromText", ST_PolygonFromText)
    FunctionRegistry.builtin.registerFunction("ST_LineStringFromText", ST_LineStringFromText)
    FunctionRegistry.builtin.registerFunction("ST_GeomFromWKT", ST_GeomFromWKT)
    FunctionRegistry.builtin.registerFunction("ST_GeomFromWKB", ST_GeomFromWKB)
    FunctionRegistry.builtin.registerFunction("ST_GeomFromGeoJSON", ST_GeomFromGeoJSON)
    FunctionRegistry.builtin.registerFunction("ST_Circle", ST_Circle)
    FunctionRegistry.builtin.registerFunction("ST_Point", ST_Point)
    FunctionRegistry.builtin.registerFunction("ST_PolygonFromEnvelope", ST_PolygonFromEnvelope)
  }

  def registerPredicates(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.registerFunction("ST_Contains", ST_Contains)
    FunctionRegistry.builtin.registerFunction("ST_Intersects", ST_Intersects)
    FunctionRegistry.builtin.registerFunction("ST_Within", ST_Within)
  }

  def registerFunctions(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.registerFunction("ST_Distance", ST_Distance)
    FunctionRegistry.builtin.registerFunction("ST_ConvexHull", ST_ConvexHull)
    FunctionRegistry.builtin.registerFunction("ST_Envelope", ST_Envelope)
    FunctionRegistry.builtin.registerFunction("ST_Length", ST_Length)
    FunctionRegistry.builtin.registerFunction("ST_Area", ST_Area)
    FunctionRegistry.builtin.registerFunction("ST_Centroid", ST_Centroid)
    FunctionRegistry.builtin.registerFunction("ST_Transform", ST_Transform)
    FunctionRegistry.builtin.registerFunction("ST_Intersection", ST_Intersection)
  }

  def registerAggregateFunctions(sparkSession: SparkSession): Unit = {
    sparkSession.udf.register("ST_Envelope_Aggr", new ST_Envelope_Aggr)
    sparkSession.udf.register("ST_Union_Aggr", new ST_Union_Aggr)
  }

  def dropConstructors(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.dropFunction("ST_PointFromText")
    FunctionRegistry.builtin.dropFunction("ST_PolygonFromText")
    FunctionRegistry.builtin.dropFunction("ST_LineStringFromText")
    FunctionRegistry.builtin.dropFunction("ST_GeomFromWKT")
    FunctionRegistry.builtin.dropFunction("ST_GeomFromWKB")
    FunctionRegistry.builtin.dropFunction("ST_GeomFromGeoJSON")
    FunctionRegistry.builtin.dropFunction("ST_Circle")
    FunctionRegistry.builtin.dropFunction("ST_Point")
    FunctionRegistry.builtin.dropFunction("ST_PolygonFromEnvelope")
  }

  def dropPredicates(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.dropFunction("ST_Contains")
    FunctionRegistry.builtin.dropFunction("ST_Intersects")
    FunctionRegistry.builtin.dropFunction("ST_Within")
  }

  def dropFunctions(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.dropFunction("ST_Distance")
    FunctionRegistry.builtin.dropFunction("ST_ConvexHull")
    FunctionRegistry.builtin.dropFunction("ST_Envelope")
    FunctionRegistry.builtin.dropFunction("ST_Length")
    FunctionRegistry.builtin.dropFunction("ST_Area")
    FunctionRegistry.builtin.dropFunction("ST_Centroid")
    FunctionRegistry.builtin.dropFunction("ST_Transform")
    FunctionRegistry.builtin.dropFunction("ST_Intersection")
  }

  def dropAggregateFunctions(sparkSession: SparkSession): Unit = {
    FunctionRegistry.builtin.dropFunction("ST_Envelope_Aggr")
    FunctionRegistry.builtin.dropFunction("ST_Union_Aggr")
  }

  def registerAll(sqlContext: SQLContext): Unit = {
    resigterConstructors(sqlContext.sparkSession)
    registerPredicates(sqlContext.sparkSession)
    registerFunctions(sqlContext.sparkSession)
    registerAggregateFunctions(sqlContext.sparkSession)
  }

  def registerAll(sparkSession: SparkSession): Unit = {
    resigterConstructors(sparkSession)
    registerPredicates(sparkSession)
    registerFunctions(sparkSession)
    registerAggregateFunctions(sparkSession)
  }

  def dropAll(sparkSession: SparkSession): Unit = {
    dropConstructors(sparkSession)
    dropPredicates(sparkSession)
    dropFunctions(sparkSession)
    dropAggregateFunctions(sparkSession)
  }
}
