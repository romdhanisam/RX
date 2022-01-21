// SPDX-FileCopyrightText: 2021 RTE FRANCE
//
// SPDX-License-Identifier: Apache-2.0

@TypeDef(
        name = "xmltype",
        defaultForType = byte[].class,
        typeClass = H2XMLTypeMapper.class
)
package com.github.rx.domain;

import com.github.rx.repository.H2XMLTypeMapper;
import org.hibernate.annotations.TypeDef;
