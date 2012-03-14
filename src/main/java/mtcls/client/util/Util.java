package mtcls.client.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

public abstract class Util {

	protected static String version;
	public static final String PATTERN_TIMESTAMP = "MM/dd/yyyy hh:mm:ss a";
	private static DateTimeFormat defaultFormat;

	public static DateTimeFormat getDefaultFormat() {
		if (defaultFormat == null) {
			defaultFormat = DateTimeFormat.getFormat(PATTERN_TIMESTAMP);
		}
		return defaultFormat;
	}

	public static String getDisplay(Date dateVal) {
		String retval = "";
		if (dateVal == null) {
			return retval;
		}
		retval = Util.getDefaultFormat().format(dateVal);
		return retval;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String v) {
		version = v;
	}

	public static long min(long a, long b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	public static int min(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	public static long max(long a, long b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static int max(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static boolean hasSpaces(String str) {
		boolean found = false;
		for (int i = 0; i < str.length() && !found; i++) {
			found = str.charAt(i) == ' ';
		}
		return found;
	}

	public static String[] tokenize(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (str != null) {
			for (int idx = str.indexOf(separator); idx != -1; idx = str
					.indexOf(separator)) {
				String token = str.substring(0, idx).trim();
				list.add(token);
				str = str.trim().substring(idx + 1).trim();
			}

			list.add(str);
		}
		return (String[]) list.toArray(new String[0]);
	}

	public static boolean contains(Object lookIn[], Object lookFor) {
		boolean found = false;
		for (int i = 0; !found && i < lookIn.length; i++)
			found = lookFor.equals(lookIn[i]);

		return found;
	}

	public static boolean contains(List<String> lookIn, Object lookFor) {
		boolean found = false;
		for (int i = 0; !found && i < lookIn.size(); i++)
			found = lookFor != null && lookFor.equals(lookIn.get(i));

		return found;
	}

	public static String getLabelWidth(String str, int multiplier) {

		String length = "100px";
		if (str != null) {
			length = Math.round(str.length() * multiplier) + "px";
		}

		return length;
	}

	public static boolean contains(Set<String> lookIn, Object lookFor) {
		boolean found = false;
		for (Iterator<String> it = lookIn.iterator(); !found && it.hasNext(); found = lookFor
				.equals(it.next()))
			;
		return found;
	}

	public static boolean endsWith(Set<String> lookIn, String lookFor) {
		boolean found = false;
		for (Iterator<String> it = lookIn.iterator(); !found && it.hasNext(); found = lookFor
				.endsWith((String) it.next()))
			;
		return found;
	}

	// public static String getPlainStyle() {
	// return "font-size:8pt;font-family:arial";
	// }

	// public static String getBoldStyle() {
	// return "font-size:8pt;font-family:arial;text-decoration:bold";
	// }

	// public static String getLinkStyle()
	// {
	// return
	// "font-size:8pt;color:blue;text-decoration:underline;cursor:hand;font-family:arial";
	// }

	public static int getScreenWidth(int offset) {
		return Window.getClientWidth() + offset;
	}

	public static int getScreenHeight(int offset) {
		return Window.getClientHeight() + offset;
	}

	// public static long getStringPixelLength(String str) {
	// long length = 100;
	// if (str != null) {
	// length = Math.round(str.length() * 6.5) ;
	// }
	// return length;
	// }

	public static String getLabel(String fieldName) {
		String result = "";
		if (!"index".equals(fieldName)) {
			if (fieldName != null && fieldName.length() > 0)
				fieldName = (new StringBuilder()).append(
						fieldName.substring(0, 1).toUpperCase()).append(
						fieldName.substring(1)).toString();
			for (int i = 0; fieldName != null && i < fieldName.length(); i++)
				if (fieldName.charAt(i) >= 'A' && fieldName.charAt(i) <= 'Z') {

					if (fieldName.charAt(i + 1) >= 'A'
							&& fieldName.charAt(i + 1) <= 'Z') {
						result = (new StringBuilder()).append(result).append(
								fieldName.substring(i, i + 1)).toString();
					} else {
						result = (new StringBuilder()).append(result).append(
								" ").append(fieldName.substring(i, i + 1))
								.toString();
					}

				} else {
					result = (new StringBuilder()).append(result).append(
							fieldName.charAt(i)).toString();
				}

		}
		int index = result.indexOf("-");
		if (index != -1)
			result = result.substring(0, index);
		return result.trim();
	}

	public static String getName(String label) {
		String result = "";
		label = label.trim();
		if (label != null && label.length() > 0)
			label = (new StringBuilder()).append(
					label.substring(0, 1).toLowerCase()).append(
					label.substring(1)).toString();
		for (int i = 0; label != null && i < label.length(); i++)
			if (label.charAt(i) != ' ')
				result = (new StringBuilder()).append(result).append(
						label.charAt(i)).toString();

		return result;
	}

	public static String capitalize(String str) {
		if (str != null && !"".equals(str)) {
			str = str.substring(0, 1).toUpperCase()
					+ str.substring(1).toLowerCase();
		}
		return str;
	}

	public static void error(Object value) {
		MessageBox.alert("Error", "" + value, null);
	}

	public static void error(Object value, Throwable throwable) {
		String msg = value + "<br/>" + throwable.getMessage();
		MessageBox.alert("Error", msg, null);
	}

	public static boolean confirm(String question) {
		return Window.confirm(question);
	}

	public static void debug(Object value) {
		Window.alert((new StringBuilder()).append("").append(value).toString());
	}

	public static void log(Object value, int level) {
		if (level == 1) {
			Window.alert((new StringBuilder()).append("").append(value)
					.toString());
		} else if (level == 2) {
			Window.setTitle((new StringBuilder()).append("").append(value)
					.toString());
		}
	}

	public static int getClientHeight() {
		return Window.getClientHeight();
	}

	public static int getClientWidth() {
		return Window.getClientWidth();
	}

	public static String formatNumber(String number) {
		String str = "";
		if (number != null && !number.trim().equals("")
				&& !number.equals("NaN")) {
			double d = (new Double(number)).doubleValue();
			str = NumberFormat.getFormat("0.####################").format(d);
		}
		return str;
	}

	public static String formatDate(Date date) {
		String value = "";
		if (date != null) {
			DateTimeFormat sdf = DateTimeFormat.getFormat("MM/dd/yyyy");
			value = sdf.format(date);
		}
		return value;
	}

	public static Date convertStringToDate(String name, String str) {
		Date retval = null;
		try {
			DateTimeFormat dtf = DateTimeFormat.getFormat(PATTERN_TIMESTAMP);
			retval = dtf.parse(str);
		} catch (Exception ignored) {
			// ignored.printStackTrace(System.err);
		}
		if (retval == null) {
			retval = convertStringToDateDecode(name, str);
		}
		return retval;
	}

	public static Date convertStringToDateDecode(String name, String str) {
		Date value = null;

		try {
			if ((str != null) & (!str.trim().equals(""))) {
				str = str.trim().substring(0, 10);
				str = str.replace(".", "");
				if (str.charAt(2) == '/') {
					DateTimeFormat sdf = DateTimeFormat.getFormat("MM/dd/yyyy");
					value = sdf.parse(str);
				} else if (str.charAt(2) == '-') {
					DateTimeFormat sdf = DateTimeFormat.getFormat("MM-dd-yyyy");
					value = sdf.parse(str);
				} else if (str.charAt(4) == '/') {
					DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy/MM/dd");
					value = sdf.parse(str);
				} else if (str.charAt(4) == '-') {
					DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd");
					value = sdf.parse(str);
				} else {
					error((new StringBuilder()).append(
							"Unsupported date format for: ").append(str)
							.toString());
				}
			}
		} catch (Exception e) {
			error("Unable to parse: " + str + " for name: " + name);
		}

		return value;
	}

	public static String formatTimestamp(Date date) {
		String value = "";
		if (date != null) {
			// DateTimeFormat sdf =
			// DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss E");
			DateTimeFormat sdf = DateTimeFormat
					.getFormat("MM/dd/yyyy HH:mm:ss");
			value = sdf.format(date);
		}
		return value;
	}

	public static Date convertStringToTimestamp(String str, String format) {
		java.util.Date value = null;

		if (str != null & !"".equals(str)) {
			try {
				DateTimeFormat sdf = DateTimeFormat.getFormat(format);
				value = sdf.parse(str);
			} catch (Exception e) {
				// continue checking
			}
		}

		return value;

	}

	public static Date convertStringToTimestamp(String str) {
		Date value = null;
		value = convertStringToTimestamp(str, "MM/dd/yyyy HH:mm:ss a");

		if (value == null) {
			value = convertStringToTimestamp(str, "MM-dd-yyyy HH:mm:ss a");
		}

		if (value == null) {
			value = convertStringToTimestamp(str, "yyyy/MM/dd HH:mm:ss a");
		}

		if (value == null) {
			value = convertStringToTimestamp(str, "yyyy-MM-dd HH:mm:ss a");
		}

		if (value == null) {
			value = convertStringToTimestamp(str,
					"EEE MMM dd HH:mm:ss zzz yyyy");
		}

		return value;
	}

	// public static Date convertStringToTimestamp2(String str) {
	// Date value = null;
	// if(str != null) {
	// str = str.substring(0, 19);
	// if(str.charAt(2) == '/') {
	// DateTimeFormat sdf = DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss");
	// value = sdf.parse(str);
	// } else if(str.charAt(2) == '-') {
	// DateTimeFormat sdf = DateTimeFormat.getFormat("MM-dd-yyyy HH:mm:ss");
	// value = sdf.parse(str);
	// } else if(str.charAt(4) == '/') {
	// DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
	// value = sdf.parse(str);
	// } else if(str.charAt(4) == '-') {
	// DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	// value = sdf.parse(str);
	// } else {
	// error((new
	// StringBuilder()).append("Unsupported timestamp format for: ").append(str).toString());
	// }
	// }
	// return value;
	// }

	public static Element getAncestor(Element element, String tagName, int count) {
		int i = 0;
		Element previous = null;
		try {
			for (; element != null && element.getParentElement() != null
					&& i < count; element = element.getParentElement()) {
				if (element.getParentElement().getTagName().indexOf(tagName) == -1)
					i++;
				previous = element;
			}

			if (element.getParentElement().getTagName().equals(tagName))
				previous = element.getParentElement();
		} catch (Exception e) {
		}
		return previous;
	}

	public static String convertDecToHex(int i) {
		String hex = "";
		int rem = i % 16;
		if (rem == 10)
			hex = (new StringBuilder()).append(hex).append("A").toString();
		else if (rem == 11)
			hex = (new StringBuilder()).append(hex).append("B").toString();
		else if (rem == 12)
			hex = (new StringBuilder()).append(hex).append("C").toString();
		else if (rem == 13)
			hex = (new StringBuilder()).append(hex).append("D").toString();
		else if (rem == 14)
			hex = (new StringBuilder()).append(hex).append("E").toString();
		else if (rem == 15)
			hex = (new StringBuilder()).append(hex).append("F").toString();
		else
			hex = (new StringBuilder()).append(hex).append(rem).toString();
		if (i > 16)
			hex = (new StringBuilder()).append(convertDecToHex(i / 16)).append(
					hex).toString();
		return hex;
	}

	public static String hexComplement(String hex) {
		String str = "";
		for (int i = 0; i < hex.length(); i++) {
			if (hex.charAt(i) == '0')
				str = (new StringBuilder()).append(str).append('F').toString();
			if (hex.charAt(i) == '1')
				str = (new StringBuilder()).append(str).append('E').toString();
			if (hex.charAt(i) == '2')
				str = (new StringBuilder()).append(str).append('D').toString();
			if (hex.charAt(i) == '3')
				str = (new StringBuilder()).append(str).append('C').toString();
			if (hex.charAt(i) == '4')
				str = (new StringBuilder()).append(str).append('B').toString();
			if (hex.charAt(i) == '5')
				str = (new StringBuilder()).append(str).append('A').toString();
			if (hex.charAt(i) == '6')
				str = (new StringBuilder()).append(str).append('9').toString();
			if (hex.charAt(i) == '7')
				str = (new StringBuilder()).append(str).append('8').toString();
			if (hex.charAt(i) == '8')
				str = (new StringBuilder()).append(str).append('7').toString();
			if (hex.charAt(i) == '9')
				str = (new StringBuilder()).append(str).append('6').toString();
			if (hex.charAt(i) == 'A')
				str = (new StringBuilder()).append(str).append('5').toString();
			if (hex.charAt(i) == 'B')
				str = (new StringBuilder()).append(str).append('4').toString();
			if (hex.charAt(i) == 'C')
				str = (new StringBuilder()).append(str).append('3').toString();
			if (hex.charAt(i) == 'D')
				str = (new StringBuilder()).append(str).append('2').toString();
			if (hex.charAt(i) == 'E')
				str = (new StringBuilder()).append(str).append('1').toString();
			if (hex.charAt(i) == 'F')
				str = (new StringBuilder()).append(str).append('0').toString();
		}

		return str;
	}

	public static String toHexString(int i, int len) {
		String hex = convertDecToHex(i);
		if (hex.length() < len)
			hex = fill(hex, "0", len - hex.length());
		hex = hex.substring(0, len);
		return hex;
	}

	public static String fill(String s, String filler, int amt) {
		for (int i = 0; i < amt; i++)
			s = (new StringBuilder()).append(filler).append(s).toString();

		return s;
	}

	public static void sleep(int time) {
		(new Timer() {

			public void run() {
			}

		}).scheduleRepeating(time);
	}

	// redirect the browser to the given url
	public static native void redirect(String url)/*-{
		try {
			$wnd.location = url;
		} catch (error) {
			// ignore any errors. Errors can occur in conjunction with the modified data check;
		}
	}-*/;

	public static native void setStatusBar(String message)/*-{
		$wnd.status = message;
	}-*/;

	// public static void relogin()
	// {
	// redirect(LOGIN_PAGE);
	// }

	// A Java method using JSNI
	public static native String getUserName() /*-{
		return $wnd.getUserName(); // $wnd is a JSNI synonym for 'window'
	}-*/;

	public static final int OFF = 0;
	public static final int DEBUG = 1;
	public static final int LOG = 2;
	public static final int TAB_KEY = 9;
}