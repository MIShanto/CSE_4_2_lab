from decimal import Decimal, getcontext
from collections import defaultdict
import json

getcontext().prec = 1000

probability = {}
range_map = {}

def generate_probabilities(message):
    char_counts = defaultdict(int)
    for ch in message:
        char_counts[ch] += 1

    pre = Decimal(0)
    print("Symbol\t\tProbability\tRange")
    for ch, count in char_counts.items():
        prob = Decimal(count) / Decimal(len(message))
        probability[ch] = prob
        range_map[ch] = (str(pre), str(pre + prob))  # Convert Decimal to string
        print(f"{ch}\t\t{prob}\t\t[{pre}\t{pre+prob})")
        pre += prob
    with open("range_map.json", "w") as f:
        json.dump(range_map, f)  # Store range_map in a file

def load_range_map():
    with open("range_map.json", "r") as f:
        range_map.clear()
        range_map.update(json.load(f))

def arithmetic_encoding(message):
    low, high, range_ = Decimal(0), Decimal(1), Decimal(1)
    for ch in message:
        high = low + range_ * Decimal(range_map[ch][1])
        low = low + range_ * Decimal(range_map[ch][0])
        range_ = high - low
    return (low + high) / Decimal(2)

def arithmetic_decoding(value):
    decoded_str = ""
    while True:
        for ch, (low, high) in range_map.items():
            if Decimal(low) <= value < Decimal(high):  # Convert low and high to Decimal
                decoded_str += ch
                rang = Decimal(high) - Decimal(low)
                value = (value - Decimal(low)) / rang
                if ch == "~":
                    return decoded_str

if __name__ == "__main__":
    with open("input.txt") as f:
        s = f.read()

    generate_probabilities(s)

    encode = arithmetic_encoding(s)
    print(f"\nEncoded Tag: {encode}")

    with open("encoded.txt", "w") as f:
        f.write(str(encode))  # Store encoded value in a file

    load_range_map()  # Load range_map from file

    decode = arithmetic_decoding(encode)
    print(f"\nDecoded String: {decode}")

    with open("decoded.txt", "w") as f:
        f.write(decode)  # Store decoded string in a file
