# swipe-control-rv-library
Library to make recyclerview swipeable


| Current Version | Status |
| ------ | ------ |
| 0.1.0 | Stable |

# Add to your android project

Add to your build.gradle project level

```sh
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Add depedencies to your build.gradle module level

```sh
 implementation "com.github.pertadima:swipe-control-rv-library:$lib_version"
```
# How to use

Default use :
```sh
  val swipeHandler = object : SwipeControl(
                this@MainActivity,
                R.drawable.ic_edit, //for left icon
                R.drawable.ic_delete //for right icon
            ) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        SWIPE_RIGHT -> {
                            editItem(viewHolder)
                        }
                        SWIPE_LEFT -> {
                            deleteItem(viewHolder)
                        }
                    }
                }
            }
```

Change background color (add this code to the constructor):
```sh
    leftBackgroundColor = "#000000"
    rightBackgroundColor = "#000000"
```

Add to recyclerview :
```sh
    val itemTouchHelper = ItemTouchHelper(swipeHandler)
    itemTouchHelper.attachToRecyclerView(rv_name)
```

# Screenshoot :
![screenshoots](http://i66.tinypic.com/2sbs5qe.png)

# Implementation :
[Room-Kotlin](https://github.com/pertadima/room-kotlin-example)


# License :
```sh
   Copyright (C) 2019 Irfan Pertadima

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
