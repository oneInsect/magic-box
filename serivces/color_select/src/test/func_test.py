
# from colormath.color_objects import SpectralColor
# from colormath.density import auto_density, ansi_density
# from colormath.density_standards import ANSI_STATUS_T_RED
#
# # Omitted the full spectral kwargs for brevity.
# color = SpectralColor(spec_340nm=0.08, ...)
# # ANSI T Density for the spectral color.
# density = auto_density(color)
#
# # Or maybe we want to specify which filter to use.
# red_density = ansi_density(color, ANSI_STATUS_T_RED)
#

import cv2
import numpy as np

green = np.uint8([[[0,255,0 ]]])
hsv_green = cv2.cvtColor(green,cv2.COLOR_LRGB2Lab)
print(hsv_green[0][0].tolist())
hsv_green = hsv_green[0][0].tolist()
import matplotlib.pyplot as plt

from PIL import Image
import matplotlib.pyplot as plt

color_map = [tuple(hsv_green)]

# 1行8子图
fig, axs = plt.subplots(1, len(color_map), figsize=(15, 15))
for i, color in enumerate(color_map):
    if len(color_map) >1 :
        ax = axs[i]
    else:
        ax = axs
    img = Image.new('RGB', (10, 10), color)
    ax.set_title(color) # 去掉刻度
    ax.set_xticks([])
    ax.set_yticks([]) # 去掉坐标轴　　# ax.axis('off')
    ax.imshow(img)
# plt.savefig(r'E:\colormap.png')
plt.show()