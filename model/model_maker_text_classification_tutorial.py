# -*- coding: utf-8 -*-
"""Model Maker Text Classification Tutorial

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/github/tensorflow/tensorflow/blob/master/tensorflow/lite/g3doc/tutorials/model_maker_text_classification.ipynb

##### Copyright 2019 The TensorFlow Authors.
"""

#@title Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""# Text classification with TensorFlow Lite Model Maker

<table class="tfo-notebook-buttons" align="left">
  <td>
    <a target="_blank" href="https://www.tensorflow.org/lite/tutorials/model_maker_text_classification"><img src="https://www.tensorflow.org/images/tf_logo_32px.png" />View on TensorFlow.org</a>
  </td>
  <td>
    <a target="_blank" href="https://colab.research.google.com/github/tensorflow/tensorflow/blob/master/tensorflow/lite/g3doc/tutorials/model_maker_text_classification.ipynb"><img src="https://www.tensorflow.org/images/colab_logo_32px.png" />Run in Google Colab</a>
  </td>
  <td>
    <a target="_blank" href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/lite/g3doc/tutorials/model_maker_text_classification.ipynb"><img src="https://www.tensorflow.org/images/GitHub-Mark-32px.png" />View source on GitHub</a>
  </td>
  <td>
    <a href="https://storage.googleapis.com/tensorflow_docs/tensorflow/tensorflow/lite/g3doc/tutorials/model_maker_text_classification.ipynb"><img src="https://www.tensorflow.org/images/download_logo_32px.png" />Download notebook</a>
  </td>
</table>

The [TensorFlow Lite Model Maker library](https://www.tensorflow.org/lite/guide/model_maker) simplifies the process of adapting and converting a TensorFlow model to particular input data when deploying this model for on-device ML applications.

This notebook shows an end-to-end example that utilizes the Model Maker library to illustrate the adaptation and conversion of a commonly-used text classification model to classify movie reviews on a mobile device. The text classification model classifies text into predefined categories. The inputs should be preprocessed text and the outputs are the probabilities of the categories. The dataset used in this tutorial are positive and negative movie reviews.

## Prerequisites

### Install the required packages
To run this example, install the required packages, including the Model Maker package from the [GitHub repo](https://github.com/tensorflow/examples/tree/master/tensorflow_examples/lite/model_maker).
"""

!pip install -q tflite-model-maker

"""Import the required packages."""

import numpy as np
import os

from tflite_model_maker import model_spec
from tflite_model_maker import text_classifier
from tflite_model_maker.config import ExportFormat
from tflite_model_maker.text_classifier import AverageWordVecSpec
from tflite_model_maker.text_classifier import DataLoader

import tensorflow as tf
assert tf.__version__.startswith('2')
tf.get_logger().setLevel('ERROR')

"""
We will load the dataset into a Pandas dataframe and change the current label names (`0` and `1`) to a more human-readable ones (`negative` and `positive`) and use them for model training.


"""

import pandas as pd

def replace_label(original_file, new_file):
  # Load the original file to pandas. We need to specify the separator as
  # '\t' as the training data is stored in TSV format
  df = pd.read_csv(original_file, sep='\t')

  # Define how we want to change the label name
  label_map =  label_map = {0: 'sadness', 1: 'joy', 2: 'fear', 3: 'love', 4: 'anger', 5: 'surprise'}

  # Excute the label change
  df.replace({'label': label_map}, inplace=True)

  # Write the updated dataset to a new file
  df.to_csv(new_file)

# Replace the label name for both the training and test dataset. Then write the
# updated CSV dataset to the current folder.
replace_label('train.tsv', 'train.csv')
replace_label('val.tsv', 'dev.csv')

"""## Quickstart

There are five steps to train a text classification model:

**Step 1. Choose a text classification model architecture.**

Here we use the average word embedding model architecture, which will produce a small and fast model with decent accuracy.
"""

spec = model_spec.get('average_word_vec')

"""Model Maker also supports other model architectures such as [BERT](https://arxiv.org/abs/1810.04805). If you are interested to learn about other architecture, see the [Choose a model architecture for Text Classifier](#scrollTo=kJ_B8fMDOhMR) section below.

**Step 2.   Load the training and test data, then preprocess them according to a specific `model_spec`.**

Model Maker can take input data in the CSV format. We will load the training and test dataset with the human-readable label name that were created earlier.

Each model architecture requires input data to be processed in a particular way. `DataLoader` reads the requirement from `model_spec` and automatically executes the necessary preprocessing.
"""

train_data = DataLoader.from_csv(
      filename='train.csv',
      text_column='sentence',
      label_column='label',
      model_spec=spec,
      is_training=True)
test_data = DataLoader.from_csv(
      filename='dev.csv',
      text_column='sentence',
      label_column='label',
      model_spec=spec,
      is_training=False)

"""**Step 3. Train the TensorFlow model with the training data.**

The average word embedding model use `batch_size = 32` by default. Therefore you will see that it takes 2104 steps to go through the 67,349 sentences in the training dataset. We will train the model for 10 epochs, which means going through the training dataset 10 times.
"""

model = text_classifier.create(train_data, model_spec=spec, epochs=30)

"""Examine the detailed model structure."""

model.summary()

"""Output predicted values for the sentences in the test set"""
model.predict_top_k(test_data, k=1, batch_size=32)

"""**Step 4. Evaluate the model with the test data.**

After training the text classification model using the sentences in the training dataset, we will use the remaining 872 sentences in the test dataset to evaluate how the model performs against new data it has never seen before.

As the default batch size is 32, it will take 28 steps to go through the 872 sentences in the test dataset.
"""

loss, acc = model.evaluate(test_data)

"""**Step 5.  Export as a TensorFlow Lite model.**

Let's export the text classification that we have trained in the TensorFlow Lite format. We will specify which folder to export the model.
By default, the float TFLite model is exported for the average word embedding model architecture.
"""

model.export(export_dir='average_word_vec')
