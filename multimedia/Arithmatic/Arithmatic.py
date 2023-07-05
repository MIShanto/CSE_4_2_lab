from decimal import Decimal, getcontext
from collections import defaultdict

getcontext().prec = 10

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
        range_map[ch] = (pre, pre + prob)
        print(f"{ch}\t\t{prob}\t\t[{pre}\t{pre+prob})")
        pre += prob
    #return probability, range_map

def arithmetic_encoding(message):
    low, high, range_ = Decimal(0), Decimal(1), Decimal(1)
    for ch in message:
        high = low + range_ * range_map[ch][1]
        low = low + range_ * range_map[ch][0]
        range_ = high - low
    return (low + high) / Decimal(2)

def arithmetic_decoding(value):
    decoded_str = ""
    while True:
        for ch, (low, high) in range_map.items():
            if low <= value < high:
                decoded_str += ch
                rang = high - low
                value = (value - low) / rang
                if ch == "~":
                    return decoded_str

if __name__ == "__main__":
    with open("input.txt") as f:
        s = f.read()

    generate_probabilities(s)
    
    encode = arithmetic_encoding(s)
    print(f"\nEncoded Tag: {encode}")

    decode = arithmetic_decoding(encode)
    print(f"\nDecoded String: {decode}")
