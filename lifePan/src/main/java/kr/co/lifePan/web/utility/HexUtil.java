package kr.co.lifePan.web.utility;

public class HexUtil {

	private static final char[] hex_data = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f'};
	private static final int[] hex_map_data = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, /* 0 ~ 9 */
		-1, -1, -1, -1, -1, -1, -1, /* invalid char */
		10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, /* A ~ Z */
		-1, -1, -1, -1, -1, -1, /* invalid char */
		10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1}; /* a ~ z */

	private static final byte[] byte_data = {
		0x00, 0X01, 0x02, 0x03, 0X04, 0x05, 0x06, 0x07, 0x08, 0x09,
		0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

	private HexUtil() {
	}

	/**
	 * Return length many bytes of the passed in byte array as a hex string.
	 *
	 * @param data the bytes to be converted.
	 * @param length the number of bytes in the data block to be converted.
	 * @return a hex representation of length bytes of data.
	 */
	public static String toHex(byte[] data, int length) {
		if (data == null || data.length == 0) {
			return "";
		}
		if (length == 0) {
			return "";
		}

		StringBuilder buf = new StringBuilder();

		for (int i = 0; i != length; i++) {
			int v = data[i] & 0xff;

			buf.append(hex_data[v >> 4]);
			buf.append(hex_data[v & 0xf]);
		}

		return buf.toString();
	}

	/**
	 * Return the passed in byte array as a hex string.
	 *
	 * @param data the bytes to be converted.
	 * @return a hex representation of data.
	 */
	public static String toHex(byte[] data) {
		if (data == null || data.length == 0) {
			return "";
		}

		return toHex(data, data.length);
	}

	/**
	 * Return the passed in byte array as a hex string.
	 *
	 * @param data the string to be converted. default encoding is UTF-8.
	 * @return a hex representation of data.
	 */
	public static String toHex(String data) {
		return toHex(data, "UTF-8");
	}

	/**
	 * Return the passed in byte array as a hex string.
	 *
	 * @param data the string to be converted.
	 * @param charsetName the string encoding.
	 * @return a hex representation of data.
	 */
	public static String toHex(String data, String charsetName) {
		if (data == null || "".equals(data.trim())) {
			return "";
		}
		try {
			byte[] bytes = data.getBytes(charsetName);
			return toHex(bytes, bytes.length);
		} catch (Exception ex) {
			return "";
		}
	}

	public static byte[] toBytes(String hex_string) {
		if (hex_string == null || hex_string.trim().equals("")) {
			return new byte[0];
		}

		byte[] original_bytes = new byte[hex_string.length() / 2 + (hex_string.length() % 2)];
		for (int i = 0; i < original_bytes.length; i++) {
			char hex_char1 = hex_string.charAt(i * 2);
			char hex_char2 = hex_string.charAt((i * 2) + 1);
			original_bytes[i] = (byte) ((byte_data[hex_map_data[hex_char1 - 48]] << 4)
					  | byte_data[hex_map_data[hex_char2 - 48]]);
		}
		return original_bytes;
	}

	public static byte[] toBytes(char[] hex_string) {
		if (hex_string == null || hex_string.length == 0 || hex_string.toString().trim().equals("")) {
			return new byte[0];
		}

		byte[] original_bytes = new byte[hex_string.length / 2 + (hex_string.length % 2)];
		for (int i = 0; i < original_bytes.length; i++) {
			char hex_char1 = hex_string[i * 2];
			char hex_char2 = hex_string[(i * 2) + 1];
			original_bytes[i] = (byte) ((byte_data[hex_map_data[hex_char1 - 48]] << 4)
					  | byte_data[hex_map_data[hex_char2 - 48]]);
		}
		return original_bytes;
	}
}
