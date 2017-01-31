package Tamaized.Voidcraft.sound;

import java.io.InputStream;

import Tamaized.Voidcraft.VoidCraft;

public class OggLength {

	public static int getLengthInSeconds(String filePath) throws Exception {
		InputStream inStream = VoidCraft.instance.getClass().getResourceAsStream(filePath);
		long dataLength = inStream.available();
		int duration = handleData(inStream, dataLength);
		inStream.close();
		return duration;
	}

	private static int handleData(InputStream inStream, long dataLength) throws Exception {
		int audio_channels;
		int audio_sample_rate;
		long headerStart;
		long sampleNum = 0;
		int vorbis_version;
		int pos = 0;
		while (true) {
			int packet_type = 0;
			char[] capture_pattern1 = { 'v', 'o', 'r', 'b', 'i', 's' };
			for (int i = 0; i < capture_pattern1.length; i++) {
				int b = inStream.read();
				if (b == -1) throw new Exception("no Vorbis identification header");
				pos++;
				if (b != capture_pattern1[i]) {
					packet_type = b;
					i = -1;
				}
			}

			if (packet_type == 1) break;
		}

		vorbis_version = read32Bits(inStream);
		if (vorbis_version > 0) throw new Exception("unknown vorbis_version " + vorbis_version);
		audio_channels = inStream.read();
		audio_sample_rate = read32Bits(inStream);
		pos += 4 + 1 + 4;

		headerStart = dataLength - 16 * 1024;
		inStream.skip(headerStart - pos);
		int count = 0;
		while (true) {
			char[] capture_pattern = { 'O', 'g', 'g', 'S', 0 };
			int i;
			for (i = 0; i < capture_pattern.length; i++) {
				int b = inStream.read();
				if (b == -1) break;
				if (b != capture_pattern[i]) {
					headerStart += i + 1;
					i = -1;
				}
			}
			if (i < capture_pattern.length) break;

			count++;

			int header_type_flag = inStream.read();
			if (header_type_flag == -1) break;

			long absolute_granule_position = 0;
			for (i = 0; i < 8; i++) {
				long b = inStream.read();
				if (b == -1) break;

				absolute_granule_position |= b << (8 * i);
			}
			if (i < 8) break;

			if ((header_type_flag & 0x06) != 0) {
				sampleNum = absolute_granule_position;
			}
		}
		if (audio_sample_rate > 0) return (int) (sampleNum / audio_sample_rate);
		else return 0;
	}

	private static int read32Bits(InputStream inStream) throws Exception {
		int n = 0;
		for (int i = 0; i < 4; i++) {
			int b = inStream.read();
			if (b == -1) throw new Exception("Unexpected end of input stream");
			n |= b << (8 * i);
		}
		return n;
	}

}
