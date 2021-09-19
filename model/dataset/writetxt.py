import csv

emotions = {'sadness\n': 0, 'joy\n': 1, 'fear\n': 2, 'love\n': 3, 'anger\n': 4, 'surprise\n': 5}

def sentences():
    in_file = open("train.txt", "r")

    out_file = open('train.tsv', 'w')
    tsv_writer = csv.writer(out_file, delimiter='\t')
    tsv_writer.writerow(['sentence', 'label'])

    for line in in_file:                            # iterates through every line in the input file
        splitted = line.split(';')
        sentence = splitted[0]
        if splitted[1] != 'surprise\n':
            emotion = emotions[splitted[1]]
            if emotion % 2 == 0:
                emotion = 0
            else:
                emotion = 1
            tsv_writer.writerow([sentence, emotion])

if __name__ == '__main__':
    sentences()
