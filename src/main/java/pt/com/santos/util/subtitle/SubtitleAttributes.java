package pt.com.santos.util.subtitle;

import pt.com.santos.util.exception.UnsupportedFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pt.com.santos.util.Pair;
import pt.com.santos.util.StringUtilities;

public class SubtitleAttributes {
    protected String name;
    protected Integer season;
    protected SortedSet<Integer> episodes;
    protected String source;
    protected String format;
    protected String quality;
    protected String group;

    public SubtitleAttributes(String name, Integer season,
            SortedSet<Integer> episodes, String source, String format,
            String quality, String group) {
        this.name = name;
        this.season = season;
        this.episodes = episodes;
        this.source = source;
        this.format = format;
        this.quality = quality;
        this.group = group;
    }

    public SubtitleAttributes(String name,
            Integer season, SortedSet<Integer> episodes) {
        this.name = name;
        this.season = season;
        this.episodes = episodes;
    }

    public SortedSet<Integer> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(SortedSet<Integer> episodes) {
        this.episodes = episodes;
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Pair<Integer, SortedSet<Integer>> getSeasonEpisodes() {
        return new Pair<Integer, SortedSet<Integer>>(season, episodes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SubtitleAttributes other = (SubtitleAttributes) obj;
        if ((this.name == null) ? (other.name != null) :
            !this.name.equals(other.name)) {
            return false;
        }
        if (this.season != other.season && (this.season == null ||
                !this.season.equals(other.season))) {
            return false;
        }
        if (this.episodes != other.episodes && (this.episodes == null ||
                !this.episodes.equals(other.episodes))) {
            return false;
        }
        if ((this.source == null) ? (other.source != null) :
            !this.source.equals(other.source)) {
            return false;
        }
        if ((this.format == null) ? (other.format != null) :
            !this.format.equals(other.format)) {
            return false;
        }
        if ((this.quality == null) ? (other.quality != null) :
            !this.quality.equals(other.quality)) {
            return false;
        }
        if ((this.group == null) ? (other.group != null) :
            !this.group.equals(other.group)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.name != null
                ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.season != null
                ? this.season.hashCode() : 0);
        hash = 97 * hash + (this.episodes != null
                ? this.episodes.hashCode() : 0);
        hash = 97 * hash + (this.source != null
                ? this.source.hashCode() : 0);
        hash = 97 * hash + (this.format != null
                ? this.format.hashCode() : 0);
        hash = 97 * hash + (this.quality != null
                ? this.quality.hashCode() : 0);
        hash = 97 * hash + (this.group != null
                ? this.group.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "{" + "name=" + name + ", " +
                "season=" + season + ", " +
                "episodes=" + episodes + ", " +
                "source=" + source + ", " +
                "format=" + format + ", " +
                "quality=" + quality + ", " +
                "group=" + group +
                "}";
    }

    public static Pair<Integer, SortedSet<Integer>>
            getSeasonAndEpisodesFromInt(int n) {
        String s = String.valueOf(n);
        return getSeasonAndEpisodesFromNum(s);
    }
    
    public static Pair<Integer, SortedSet<Integer>>
            getSeasonAndEpisodesFromNum(String s) {
        Integer season = null;
        SortedSet<Integer> eps = new TreeSet<Integer>();
        if (s.length() >= 3) {
            int endS = 1;
            if (s.length() % 2 == 0) {
                endS = 2;
            }
            season = Integer.parseInt(s.substring(0, endS));
            s = s.substring(endS);
            while (!s.isEmpty()) {
                int ep = Integer.parseInt(s.substring(0, 2));
                eps.add(ep);
                s = s.substring(2);
            }

            if (season == null || eps.isEmpty()) return null;

            return new Pair<Integer, SortedSet<Integer>>(season, eps);
        }
        return null;
    }

    public static String getName(String name)
            throws UnsupportedFormatException
    {
        String lowerName = name.toLowerCase();

        /////////////////////////////////////////
        String[] regexs = {
            "tpz-(4400)\\d{3,4}\\D*", // special
            "tpz-(4400)\\d{1,2}(\\d{2})+\\D*", // special
            "tpz-(4400)\\d{3,4}(-\\d{2})+\\D*", // special
            "tpz-(\\D*)\\d{3,4}\\D*", // special
            "tpz-(\\D*)\\d{1,2}(\\d{2})+\\D*", // special
            "tpz-(\\D*)\\d{3,4}(-\\d{2})+\\D*", // special
            "(.*)[\\. ]\\[\\d{1,2}[eExX]\\d{1,2}\\].*",
            "(.*)[\\. ][vVsS]\\d{1,2}([&eExX]\\d{1,2}([\\.-][&eExX]\\d{1,2})+)r?[\\. ].*",
            "(.*)[\\. ][vVsS]\\d{1,2}([&eExX]\\d{1,2})+r?[\\. ].*",
            "(.*)- \\d{1,2}([&eExX]\\d{1,2})+ -.*",
            "(.*)[\\._-][vVsS]?(\\d{1,2})([&eExX]\\d{1,2}([\\.-]\\d{1,2})+)[\\._-].*",
            "(.*)[\\._-][vVsS]?\\d{1,2}([&eExX]\\d{1,2}([\\.-][&eExX]\\d{1,2})+)[\\._-].*",
            "(.*)[\\._-][vVsS]?\\d{1,2}([&eExX]\\d{1,2})+[\\._-].*",
            "(.*)[\\._-]\\d{3,4}[\\._-].*",
            "(.*)[\\._-] \\d{3,4} [\\._-].*",
            "(.*)[\\._-]\\d{3,4}([_-]\\d{1,2})+[\\._-].*",
            "(.*)[\\. ][vVsS]\\d{1,2}[eExX]\\d{1,2}([-_][&eExX]?\\d{1,2})+[\\. ].*",
            "(.*) \\d{1,2}[eExX]\\d{1,2}.*"
        };

        Matcher m = null;
        for (String regex : regexs) {
            m = Pattern.compile(regex,
                    Pattern.CASE_INSENSITIVE).matcher(lowerName);
            if (m.matches()) break;
            m = null;
        }

        if (m != null) {
            String res = m.group(1).
                    replace(".", " ").replace("_"," ").trim();
                    //.replace("-", " ").trim();
            if (!res.isEmpty()) return res;
        }

        throw new UnsupportedFormatException(name);

        /////////////////////////////////////////

        /*Matcher m1 = Pattern.compile("(.*)\\.s\\d\\d?(e\\d\\d?)+\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m2 = Pattern.compile("(.*) s\\d\\d?e\\d\\d? .*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m3 = Pattern.compile("(.*)- \\d\\d?x\\d\\d?(-\\d\\d?)* -.*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m4 = Pattern.compile("(.*)\\.\\d\\d?\\d\\d\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m5 = Pattern.compile("(.*)\\.s\\d\\d?e\\d\\d?(-e\\d\\d?)*\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m6 = Pattern.compile("(.*)\\.s\\d\\d?e\\d\\d?(-\\d\\d?)*\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m7 = Pattern.compile("(.*)_\\d\\d?x\\d\\d?_.*",
                Pattern.CASE_INSENSITIVE).matcher(name);

        Matcher m = null;
        if (m1.matches()) {
            m = m1;
        } else if (m2.matches()) {
            m = m2;
        } else if (m3.matches()) {
            m = m3;
        } else if (m4.matches()) {
            m = m4;
        } else if (m5.matches()) {
            m = m5;
        } else if (m6.matches()) {
            m = m6;
        } else if (m7.matches()) {
            m = m7;
        } else {
            throw new UnsupportedFormatException(torrentName);
        }

        String s = m.group(1);

        return s.replace(".", " ").trim();*/
    }

    public static SortedSet<Integer> getEps(String s, int len) {
        SortedSet<Integer> res = new TreeSet<Integer>();
        String number = "";
        char[] chs = s.toCharArray();
        int i = 0;
        while (i < chs.length) {
            char c = chs[i];
            if (c >= '0' && c <= '9')
                number += c;
            if (number.length() == len) {
                res.add(Integer.parseInt(number));
                number = "";
            }
            i++;
        }
        if (res.isEmpty()) return null;
        else return res;
    }

    public static
            Pair<Integer, SortedSet<Integer>> getSeasonAndEpisodes(String name)
            throws UnsupportedFormatException {
        String lowerName = name.toLowerCase();

        String[] regexs = {
            "tpz-(4400)(\\d{3,})\\D*", // special //0
            "tpz-(4400)(\\d{3,4})((-\\d{2})+)\\D*", // special //1
            "tpz-(\\D*)(\\d{3,})\\D*", // special //2
            "tpz-(\\D*)(\\d{3,4})((-\\d{2})+)\\D*", // special //3
            "(.*)[\\. ]\\[(\\d{1,2})[eExX](\\d{1,2})\\].*", //4
            "(.*)[\\. ][vVsS](\\d{1,2})([&eExX]\\d{1,2}([\\.-][&eExX]\\d{1,2})+)r?[\\. ].*", //5
            "(.*)[\\. ][vVsS](\\d{1,2})(([&eExX]\\d{1,2})+)r?[\\. ].*", //6
            "(.*)- (\\d{1,2})(([&eExX]\\d{1,2})+) -.*", //7
            "(.*)[\\._-][vVsS]?(\\d{1,2})([&eExX]\\d{1,2}([\\.-]\\d{1,2})+)[\\._-].*", //8
            "(.*)[\\._-][vVsS]?(\\d{1,2})([&eExX]\\d{1,2}([\\.-][&eExX]\\d{1,2})+)[\\._-].*", //9
            "(.*)[\\._-][vVsS]?(\\d{1,2})(([&eExX]\\d{1,2})+)[\\._-].*", //10
            "(.*)[\\._-](\\d{3,4})[\\._-].*", //11
            "(.*)[\\._-] (\\d{3,4}) [\\._-].*", //12
            "(.*)[\\._-](\\d{3,4})(([_-]\\d{1,2})+)[\\._-].*", //13
            "(.*)[\\. ][vVsS](\\d{1,2})([eExX]\\d{1,2}([-_][&eExX]?\\d{1,2})+)[\\. ].*", //14
            "(.*) (\\d{1,2})[eExX](\\d{1,2}).*" //15
        };

        Matcher m = null;
        int i = 0;
        for (String regex : regexs) {
            m = Pattern.compile(regex,
                    Pattern.CASE_INSENSITIVE).matcher(lowerName);
            if (m.matches()) break;
            m = null;
            i++;
        }

        Integer season = null;
        SortedSet<Integer> episodes = new TreeSet<Integer>();

        if (m != null) {
            switch (i) {
                case 0:
                case 2:
                case 11:
                case 12: {
                    Pair<Integer, SortedSet<Integer>> p =
                            getSeasonAndEpisodesFromNum(m.group(2));
                    season = p.getFst();
                    episodes = p.getSnd();

                    break;
                }
                case 1:
                case 3:
                case 13: {
                    Pair<Integer, SortedSet<Integer>> p =
                            getSeasonAndEpisodesFromNum(m.group(2));
                    if (p == null) break;
                    SortedSet<Integer> eps = getEps(m.group(3), 2);
                    if (eps == null) break;

                    season = p.getFst();
                    episodes = p.getSnd();
                    episodes.addAll(eps);

                    break;
                }
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 14:
                case 15: {
                    season = Integer.parseInt(m.group(2));

                    SortedSet<Integer> eps = getEps(m.group(3), 2);

                    if (eps == null) break;

                    episodes.addAll(eps);

                    break;
                }
                default:
                    break;
            }
        }

        if (season == null || episodes.isEmpty())
            throw new UnsupportedFormatException(name);

        return new Pair<Integer, SortedSet<Integer>>(season, episodes);
    }

    public static 
            Pair<Integer, SortedSet<Integer>> getSE(String
            fileName) throws UnsupportedFormatException {
        String name = fileName.toLowerCase();
        Matcher m1 = Pattern.compile(".*\\.s\\d\\d?(e\\d\\d?)+\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m2 = Pattern.compile(".* s\\d\\d?e\\d\\d? .*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m3 = Pattern.compile(".*- \\d\\d?x\\d\\d?(-\\d\\d?)* -.*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m4 = Pattern.compile(".*\\.\\d\\d?\\d\\d\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m5 = Pattern.compile(".*\\.s\\d\\d?e\\d\\d?(-e\\d\\d?)*\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m6 = Pattern.compile(".*\\.s\\d\\d?e\\d\\d?(-\\d\\d?)*\\..*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        Matcher m7 = Pattern.compile(".*_\\d\\d?x\\d\\d?_.*",
                Pattern.CASE_INSENSITIVE).matcher(name);
        String theSeason = null;
        List<String> episodes = null;
        if (m1.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst("\\.s\\d\\d?(e\\d\\d?)+\\.", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf(".");
            name = name.substring(0, index);
            index = name.indexOf("e");
            theSeason = name.substring(1, index);
            String curr = name;
            while((index = curr.indexOf("e")) != -1) {
                String subs = curr.substring(index + 1);
                int index2 = subs.indexOf("e");
                if (index2 != -1)
                    episodes.add(subs.substring(0, index2));
                else episodes.add(subs);
                curr = subs;
            }
        } else if (m2.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst(" s\\d\\d?e\\d\\d? ", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf(" ");
            name = name.substring(0, index);
            index = name.indexOf("e");
            theSeason = name.substring(1, index);
            episodes.add(name.substring(index + 1));
        } else if (m3.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst("- \\d\\d?x\\d\\d?(-\\d\\d?)* -", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 2);
            index = name.indexOf(" -");
            name = name.substring(0, index);
            index = name.indexOf("x");
            theSeason = name.substring(0, index);
            String curr = name;
            String check = "x";
            while((index = curr.indexOf(check)) != -1) {
                String subs = curr.substring(index + 1);
                check = "-";
                int index2 = subs.indexOf(check);
                if (index2 != -1)
                    episodes.add(subs.substring(0, index2));
                else episodes.add(subs);
                curr = subs;
            }
        } else if (m4.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst("\\.\\d\\d?\\d\\d\\.", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf(".");
            name = name.substring(0, index);
            int cut = name.length() / 2;
            theSeason = name.substring(0, cut);
            episodes.add(name.substring(cut));
        } else if (m5.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst(
                    "\\.s\\d\\d?e\\d\\d?(-e\\d\\d?)*\\.", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf(".");
            name = name.substring(0, index);
            index = name.indexOf("e");
            theSeason = name.substring(1, index);
            String curr = name;
            while((index = curr.indexOf("e")) != -1) {
                String subs = curr.substring(index + 1);
                int index2 = subs.indexOf("-e");
                if (index2 != -1)
                    episodes.add(subs.substring(0, index2));
                else episodes.add(subs);
                curr = subs;
            }
        } else if (m6.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst(
                    "\\.s\\d\\d?e\\d\\d?(-\\d\\d?)*\\.", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf(".");
            name = name.substring(0, index);
            index = name.indexOf("e");
            theSeason = name.substring(1, index);
            String curr = name;
            String search = "e";
            while((index = curr.indexOf(search)) != -1) {
                String subs = curr.substring(index + 1);
                search = "-";
                int index2 = subs.indexOf(search);
                if (index2 != -1)
                    episodes.add(subs.substring(0, index2));
                else episodes.add(subs);
                curr = subs;
            }
        } else if (m7.matches()) {
            episodes = new LinkedList<String>();
            String s = name.replaceFirst("_\\d\\d?x\\d\\d?_", "+");
            int index = s.indexOf("+");
            name = name.substring(index + 1);
            index = name.indexOf("_");
            name = name.substring(0, index);
            index = name.indexOf("x");
            theSeason = name.substring(0, index);
            episodes.add(name.substring(index + 1));
        }

        if (theSeason == null || episodes.isEmpty())
            throw new UnsupportedFormatException(fileName);

        int seasonN =  Integer.parseInt(theSeason);
        SortedSet<Integer> eplist = new TreeSet<Integer>();
        for (String ep : episodes) {
            eplist.add(Integer.parseInt(ep));
        }
        return new Pair<Integer, SortedSet<Integer>>(seasonN, eplist);
    }

    public static SubtitleAttributes getProperties(String name)
            throws UnsupportedFormatException
    {
        String nname = getName(name);
        Pair<Integer, SortedSet<Integer>> thisPair =
                getSeasonAndEpisodes(name);

        /*String nameLower = name.toLowerCase();
        Matcher m1 = Pattern.compile(
                ".*\\.([^\\p{Punct}]*)\\.([^\\p{Punct}]*)" +
                "\\.([^\\p{Punct}]*)-([^\\p{Punct}]*)",
                Pattern.CASE_INSENSITIVE).matcher(nameLower);

        String quality = null;
        String source = null;
        String format = null;
        String group = null;
        if (m1.matches()) {
            quality = m1.group(1);
            source = m1.group(2);
            format = m1.group(3);
            group = m1.group(4);
            /*System.out.println(quality);
            System.out.println(source);
            System.out.println(format);
            System.out.println(group);*
            if (!(quality.compareToIgnoreCase("480i") == 0 ||
                    quality.compareToIgnoreCase("576i") == 0 ||
                    quality.compareToIgnoreCase("576p") == 0 ||
                    quality.compareToIgnoreCase("720p") == 0 ||
                    quality.compareToIgnoreCase("1080i") == 0 ||
                    quality.compareToIgnoreCase("1080p") == 0))
                quality = null;
        }*/
        String[] arr = fetch(name);

        return new SubtitleAttributes(nname, thisPair.getFst(),
                thisPair.getSnd(), arr[1], arr[2], arr[0], arr[3]);
    }

    public static String[] fetch(String name) {
        String nameLower = name.toLowerCase();

        /*if (nameLower.endsWith("[vtv]"))
            nameLower = nameLower.substring(0, nameLower.)*/
        nameLower = StringUtilities.removeFromEnd(nameLower, "[tvt]");
        nameLower = StringUtilities.removeFromEnd(nameLower, "[vtv]");
        nameLower = StringUtilities.removeFromEnd(nameLower, "proper");
        nameLower = StringUtilities.removeFromEnd(nameLower, "repack");
        nameLower = StringUtilities.removeFromEnd(nameLower, ".");

        String[] regexs = {
            "tpz-(.*)",
            ".*\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+).repack-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([\\p{Alpha}_]+)_([^\\p{Punct}]+).repack-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+).repack-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)_([^\\p{Punct}]+)[._]repack-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([\\p{Alpha}_]+)_([^\\p{Punct}]+)-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)_([^\\p{Punct}]+)-([^\\p{Punct}]+)",
            ".*\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)\\.([^\\p{Punct}]+)"
        };

        Matcher m = null;
        int i = 0;
        for (String regex : regexs) {
            m = Pattern.compile(regex,
                    Pattern.CASE_INSENSITIVE).matcher(nameLower);
            if (m.matches()) break;
            m = null;
            i++;
        }

        /*Matcher m1 = Pattern.compile(
                ".*\\.([^\\p{Punct}]*)\\.([^\\p{Punct}]*)" +
                "\\.([^\\p{Punct}]*)-([^\\p{Punct}]*)",
                Pattern.CASE_INSENSITIVE).matcher(nameLower);*/

        String quality = null;
        String source = null;
        String format = null;
        String group = null;
        if (m != null) {
        //if (m1.matches()) {
            if (m.groupCount() == 1) {
                source = "dvdrip";
                format = "xvid";
                group = "topaz";
            } else if(m.groupCount() >= 4) {
                quality = m.group(1);
                source = m.group(2);
                format = m.group(3);
                group = m.group(4);
            } else {
                source = m.group(1);
                format = m.group(2);
                group = m.group(3);
            }

            if (source != null) {
                String[] filers = {
                    "[sS]\\d{1,2}[eExX]\\d{1,2}",
                    "\\d{3,}"
                };
                Matcher m1 = null;
                for (String regex : filers) {
                    m1 = Pattern.compile(regex,
                    Pattern.CASE_INSENSITIVE).matcher(source);
                    if (m1.matches()) break;
                    m1 = null;
                }
                if (m1 != null) source = null;
            }

            if ((source == null || source.equals("repack"))
                    && format != null
                    && format.equals("hdtv")) {
                source = format;
                format = null;
            }
            /*System.out.println(quality);
            System.out.println(source);
            System.out.println(format);
            System.out.println(group);*/
            if (quality != null &&
                    !(quality.compareToIgnoreCase("480i") == 0 ||
                    quality.compareToIgnoreCase("576i") == 0 ||
                    quality.compareToIgnoreCase("576p") == 0 ||
                    quality.compareToIgnoreCase("720p") == 0 ||
                    quality.compareToIgnoreCase("1080i") == 0 ||
                    quality.compareToIgnoreCase("1080p") == 0))
                quality = null;
        }

        //System.out.println("Q: " + quality + " S: " + source + " F: " + format
                //+ " G: " + group);

        return new String[]{ quality, source, format, group };
    }
}

