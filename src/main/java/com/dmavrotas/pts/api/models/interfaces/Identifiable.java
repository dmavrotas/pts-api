package com.dmavrotas.pts.api.models.interfaces;

import com.dmavrotas.pts.api.utils.AssignedIdentityGenerator;

/**
 * @GenericGenerator(name = "assigned-identity", strategy = "com.kinaxia.kds.repository.utils.AssignedIdentityGenerator")
 * @GeneratedValue(generator = "assigned-identity", strategy = GenerationType.IDENTITY)
 * @see AssignedIdentityGenerator
 * <p>
 * to be used with
 */
public interface Identifiable
{
    Integer getId();
}
