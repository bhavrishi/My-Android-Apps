package com.inclass.uncc.previossax;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;

/**
 * Created by Rishi on 01/10/17.
 */

public class WeatherUTil {
    static public class PSAXParser {

        /*
               static public ArrayList<Weather> parseParser(InputStream in) throws IOException, SAXException, XmlPullParserException {
                  XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                   parser.setInput(in, "UTF-8");
                   ArrayList<Weather> wlist = new ArrayList<Weather>();
                   Weather weather = null;
                   int event = parser.getEventType();
                   while (event != XmlPullParser.END_DOCUMENT) {
                       switch (event) {
                           case XmlPullParser.START_TAG:
                               if (parser.getName().equals("current")) {
                                   weather = new Weather();

                               }
                               if(parser.getName().equals("city"))
                                   weather.setCityid(parser.getAttributeValue(null, "id"));
                               if(parser.getName().equals("speed"))
                                   weather.setWindSpeed(parser.getAttributeValue(null, "value"));
                               if(parser.getName().equals("direction"))
                                   weather.setWindDirection(parser.getAttributeValue(null, "value"));
                               if(parser.getName().equals("precipitation"))
                                   weather.setPrecipitation(parser.getAttributeValue(null, "mode"));

                              /* else if(parser.getName().equals("city"))
                                weather.setName(parser.nextText().toString());*/
                     /*      break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("current")) {
                            wlist.add(weather);
                            weather = null;
                        }
                        break;
                    default:break;
                }


                event = parser.next();
            }


            return wlist;

        }


    }
}*/
        ArrayList<Weather> wlist;
        Weather weather;
        //Wind wind;
        StringBuilder sb;

        static public ArrayList<Weather> parseParser(InputStream in) throws IOException, SAXException {

            PSAXParser parser = new PSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);
            return parser.getWlist();


        }
/*        <current>
<city id="2643743" name="London">
<coord lon="-0.13" lat="51.51"/>
<country>GB</country>
<sun rise="2017-01-30T07:40:36" set="2017-01-30T16:47:56"/>
</city>
<temperature value="280.15" min="278.15" max="281.15" unit="kelvin"/>
<humidity value="81" unit="%"/>
<pressure value="1012" unit="hPa"/>
<wind>
<speed value="4.6" name="Gentle Breeze"/>
<gusts/>
<direction value="90" code="E" name="East"/>
</wind>
<clouds value="90" name="overcast clouds"/>
<visibility value="10000"/>
<precipitation mode="no"/>
<weather number="701" value="mist" icon="50d"/>
<lastupdat*/

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            wlist = new ArrayList<Weather>();
            sb = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (localName == "current") {
                weather = new Weather();
            }
            if (localName == "city") {
                weather.setCityid(attributes.getValue("id"));
                weather.setName(attributes.getValue("name"));
            }
          *//* if (localName == "wind")

                {*//*
                    if (localName == "speed") {
                        weather.setWindSpeed(attributes.getValue("value"));
                    }
            if (localName == "direction") {
                weather.setWindDirection(attributes.getValue("value"));
            }

            if (localName == "temperature")
                weather.setTemperature(attributes.getValue("value"));
            if (localName == "humidity")
                weather.setHumidity(attributes.getValue("value"));
            if (localName == "pressure")
                weather.setPressure(attributes.getValue("value"));
            if (localName == "clouds")
                weather.setClouds(attributes.getValue("value"));
            if (localName == "precipitation")
                weather.setPrecipitation(attributes.getValue("mode"));

        }

        public ArrayList<Weather> getWlist() {
            return wlist;
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();

        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName == "current") {
                wlist.add(weather);

            }
           *//* if(localName.equals("wind"))
            {
                weather.setWind(wind);
            }*//*
*//*else if(localName.equals("speed"))
            {
               weather.setWindSpeed(sb.toString());
            }*//*
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }
    }
}
