import os

original_size = os.path.getsize('RLE_in.txt')
compressed_size = os.path.getsize('RLE_out.txt')

compression_ratio = original_size / compressed_size
print(f'Compression ratio: {compression_ratio * 100}')