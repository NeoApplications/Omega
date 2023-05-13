/*
 * This file is part of Neo Launcher
 * Copyright (c) 2023   Neo Launcher Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.saggitt.omega.groups

import android.content.Context
import com.android.launcher3.util.ComponentKey

abstract class Filter<T>(val context: Context) {

    abstract val matches: Set<T>?

    open val size get() = matches?.size ?: 0

    //abstract val matcher: Predicate<ItemInfo>
    abstract val matcher: CustomItemInfoMatcher
}

class CustomFilter(context: Context, override val matches: Set<ComponentKey>) :
    Filter<ComponentKey>(context) {

    override val matcher: CustomItemInfoMatcher
        get() = CustomItemInfoMatcher { info, _ ->
            matches.contains(
                ComponentKey(
                    info.targetComponent,
                    info.user
                )
            )
        }
}
/*class CustomFilter(context: Context, override val matches: Set<ComponentKey>) :
    Filter<ComponentKey>(context) {

    override val matcher
        get() = ItemInfoMatcher.ofComponents(
            matches.map { it.componentName }.toSet().toHashSet(), matches.map { it.user }[0]
        )
}*/